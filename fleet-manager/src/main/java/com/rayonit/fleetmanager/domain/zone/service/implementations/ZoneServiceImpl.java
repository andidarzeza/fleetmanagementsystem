package com.rayonit.fleetmanager.domain.zone.service.implementations;

import com.rayonit.fleetmanager.domain.zone.model.Zone;
import com.rayonit.fleetmanager.domain.zone.repository.ZoneRepository;
import com.rayonit.fleetmanager.domain.zone.service.interfaces.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public ResponseEntity<Object> addZone(Zone zone) {
        return null;
    }
}
