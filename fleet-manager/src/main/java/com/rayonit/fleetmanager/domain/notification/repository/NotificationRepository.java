package com.rayonit.fleetmanager.domain.notification.repository;

import com.rayonit.fleetmanager.domain.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllByUserID(String userID);
    List<Notification> findAllByVehicleID(String vehicleID);
}
