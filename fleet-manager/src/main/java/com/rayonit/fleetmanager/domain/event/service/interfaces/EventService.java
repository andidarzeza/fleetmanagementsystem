package com.rayonit.fleetmanager.domain.event.service.interfaces;


import com.rayonit.fleetmanager.domain.event.model.Event;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventService {
    Mono<Event> addEvent(Event event);
    Flux<Event> getUserEvents(String userID);

}
