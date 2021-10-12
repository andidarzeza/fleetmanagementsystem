package com.rayonit.fleetmanager.domain.notification.service.interfaces;


import com.rayonit.fleetmanager.domain.notification.model.Notification;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;

import java.util.List;

public interface NotificationService {
    void sendNotification(Notification notification);
    List<Notification> getUserNotifications(String userID);
    void deleteNotification(String notificationID, String condition);
    void deleteNotificationsForEachVehicle(List<Vehicle> vehicles);
}
