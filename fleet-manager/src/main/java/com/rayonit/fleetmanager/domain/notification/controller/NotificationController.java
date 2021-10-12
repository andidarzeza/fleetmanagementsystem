package com.rayonit.fleetmanager.domain.notification.controller;

import com.rayonit.fleetmanager.domain.notification.model.Notification;
import com.rayonit.fleetmanager.domain.notification.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userID:.+}")
    public List<Notification> getUserNotifications(@PathVariable String userID){
        return notificationService.getUserNotifications(userID);
    }
}
