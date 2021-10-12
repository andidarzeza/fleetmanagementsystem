package com.rayonit.fleetmanager.domain.user.converter;

import com.rayonit.fleetmanager.domain.user.dto.UserRequest;
import com.rayonit.fleetmanager.domain.user.dto.UserResponse;
import com.rayonit.fleetmanager.domain.user.model.Role;
import com.rayonit.fleetmanager.domain.user.model.User;
import com.rayonit.fleetmanager.domain.vehicle.dto.VehicleRequest;
import com.rayonit.fleetmanager.domain.vehicle.dto.VehicleResponse;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter implements Converter<User, UserRequest, UserResponse> {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User convertToModel(UserRequest request) {
        return new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER
        );
    }

    @Override
    public UserResponse convertToResponse(User model) {
        return new UserResponse(model.getUsername(), model.getRole());
    }

    @Override
    public List<UserResponse> convertToResponseList(Collection<User> list) {
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
}
