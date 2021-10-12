package com.rayonit.fleetmanager.domain.user.service.interfaces;
import com.rayonit.fleetmanager.domain.user.dto.UserRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface UserService {
    ResponseEntity<Object> register(UserRequest userRequest);
    ResponseEntity<Object> login(UserRequest userRequest);
    ResponseEntity<Object> getAllUsers();
    ResponseEntity<Object> deleteUser(String userID);
    ResponseEntity<Object> updateUser(String newUsername, String username);
}
