package com.rayonit.fleetmanager.domain.event.controller;

import com.rayonit.fleetmanager.domain.event.model.Event;
import com.rayonit.fleetmanager.domain.event.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/{userID:.+}", produces = "text/event-stream")
    public Flux<Event> getUserEvents(@PathVariable String userID){
        return eventService.getUserEvents(userID);
    }

}
