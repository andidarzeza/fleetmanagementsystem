package com.rayonit.fleetmanager.domain.event.repository;

import com.rayonit.fleetmanager.domain.event.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EventRepository extends ReactiveMongoRepository<Event, String> {

    @Tailable
    Flux<Event> findAllByUserID(String userID);
}
