package com.rayonit.fleetmanager.domain.user.service.implementations;

import com.rayonit.fleetmanager.domain.company.model.Company;
import com.rayonit.fleetmanager.domain.company.service.interfaces.CompanyService;
import com.rayonit.fleetmanager.domain.user.dto.UserRequest;
import com.rayonit.fleetmanager.domain.user.dto.UserResponse;
import com.rayonit.fleetmanager.domain.user.model.User;
import com.rayonit.fleetmanager.domain.user.repository.UserRepository;
import com.rayonit.fleetmanager.domain.user.service.interfaces.UserService;
import com.rayonit.fleetmanager.exceptions.UsernameAlreadyInUseException;
import com.rayonit.fleetmanager.domain.user.dto.LoginResponse;
import com.rayonit.fleetmanager.domain.user.model.Role;
import com.rayonit.fleetmanager.general.classes.Message;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import com.rayonit.fleetmanager.security.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtManager jwtManager;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private Converter<User, UserRequest, UserResponse> converter;

    @Override
    public ResponseEntity<Object> register(UserRequest userRequest) {
        if (userRequest == null){
            return new ResponseEntity<>(new Message("No Object of User was provided!"), HttpStatus.NOT_FOUND);
        }else{
            User existingUser = repository.findByUsername(userRequest.getUsername());
            if (existingUser != null) throw new UsernameAlreadyInUseException("Username already in use!", HttpStatus.CONFLICT);
            User user = converter.convertToModel(userRequest);
            repository.save(user);
            return new ResponseEntity<>(new Message("Registration Successful"), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Object> login(UserRequest userRequest) {
        if (userRequest != null){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
            User user = repository.findByUsername(userRequest.getUsername());
            final String jwt = jwtManager.createToken(user);
            return new ResponseEntity<>(new LoginResponse(userRequest.getUsername(), jwt), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("No Object of User was provided"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(converter.convertToResponseList(repository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteUser(String userID) {
        User user = repository.findByUsername(userID);
        if(user == null) return new ResponseEntity<>(new Message("Could't find user with username: " + userID), HttpStatus.NOT_FOUND);
        List<Company> companiesToBeDeleted = this.companyService.getUserCompanies(user.getUsername());
        companiesToBeDeleted.forEach(company -> companyService.deleteCompany(company.getCompanyID()));
        repository.delete(user);
        return new ResponseEntity<>(converter.convertToResponse(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateUser(String newUsername, String username) {
        User user = repository.findByUsername(username);
        if(user == null) return new ResponseEntity<>(new Message("Could't find user with username: " + username), HttpStatus.NOT_FOUND);
        user.setUsername(newUsername);
        repository.save(user);
        return new ResponseEntity<>(new Message("Changes made!"), HttpStatus.OK);
    }
}
