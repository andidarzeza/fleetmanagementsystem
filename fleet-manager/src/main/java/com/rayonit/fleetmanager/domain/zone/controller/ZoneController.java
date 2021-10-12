package com.rayonit.fleetmanager.domain.zone.controller;

import com.rayonit.fleetmanager.domain.zone.model.Zone;
import com.rayonit.fleetmanager.domain.zone.service.interfaces.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/zones")
@CrossOrigin(origins = "http://localhost:4200")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

}
