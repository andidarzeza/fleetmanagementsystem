package com.rayonit.fleetmanager.domain.user.service.implementations;

import com.rayonit.fleetmanager.domain.user.model.User;
import com.rayonit.fleetmanager.domain.user.model.UserPrincipal;
import com.rayonit.fleetmanager.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("Error 404");

        return new UserPrincipal(user);
    }
}
