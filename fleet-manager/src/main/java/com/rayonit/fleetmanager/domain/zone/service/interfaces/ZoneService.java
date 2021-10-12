package com.rayonit.fleetmanager.domain.zone.service.interfaces;


import com.rayonit.fleetmanager.domain.zone.model.Zone;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ZoneService {
    ResponseEntity<Object> addZone(Zone zone);
}
