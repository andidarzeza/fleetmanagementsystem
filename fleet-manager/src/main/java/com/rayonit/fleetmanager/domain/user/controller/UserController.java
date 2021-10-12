package com.rayonit.fleetmanager.domain.user.controller;

import com.rayonit.fleetmanager.domain.user.repository.UserRepository;
import com.rayonit.fleetmanager.domain.user.service.interfaces.UserService;
import com.rayonit.fleetmanager.domain.user.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequest userRequest){
        return userService.register(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserRequest userRequest){
        return userService.login(userRequest);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<Object> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/admin/delete/{userID:.+}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userID){
        return userService.deleteUser(userID);
    }

    @PutMapping("/admin/update/{userID:.+}")
    public ResponseEntity<Object> updateUser(@RequestParam("newUsername") String newUsername, @PathVariable String userID){
        return userService.updateUser(newUsername, userID);
    }
}
