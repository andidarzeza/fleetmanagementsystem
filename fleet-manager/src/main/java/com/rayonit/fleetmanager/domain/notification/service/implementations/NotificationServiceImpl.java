package com.rayonit.fleetmanager.domain.notification.service.implementations;

import com.rayonit.fleetmanager.domain.notification.model.Notification;
import com.rayonit.fleetmanager.domain.notification.repository.NotificationRepository;
import com.rayonit.fleetmanager.domain.notification.service.interfaces.NotificationService;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationServiceImpl() {
    }

    @Override
    public void sendNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getUserNotifications(String userID) {
        return notificationRepository.findAllByUserID(userID);
    }

    @Override
    public void deleteNotification(String vehicleID, String condition) {
        List<Notification> notifications = notificationRepository.findAllByVehicleID(vehicleID);
        notifications.forEach(notification -> {
            if (notification.getMessage().equals(condition)){
                notificationRepository.delete(notification);
            }
        });
    }

    @Override
    public void deleteNotificationsForEachVehicle(List<Vehicle> vehicles) {
        vehicles.forEach(vehicle -> {
            List<Notification> notifications = notificationRepository.findAllByVehicleID(vehicle.getVehicleID());
            notificationRepository.deleteAll(notifications);
        });
    }
}
