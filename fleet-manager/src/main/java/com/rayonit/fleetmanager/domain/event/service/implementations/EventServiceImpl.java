package com.rayonit.fleetmanager.domain.event.service.implementations;

import com.rayonit.fleetmanager.domain.event.model.Event;
import com.rayonit.fleetmanager.domain.event.repository.EventRepository;
import com.rayonit.fleetmanager.domain.event.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Mono<Event> addEvent(Event event) {
        eventRepository.save(event).subscribe();
        return null;
    }

    @Override
    public Flux<Event> getUserEvents(String userID) {
        return eventRepository.findAllByUserID(userID);
    }
}
