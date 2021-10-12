package com.rayonit.fleetmanager.domain.fleet.service.interfaces;


import com.rayonit.fleetmanager.domain.fleet.dto.FleetRequest;
import com.rayonit.fleetmanager.domain.fleet.model.Fleet;
import org.springframework.http.ResponseEntity;

public interface FleetService {

    ResponseEntity<Object> getAllFleetsByCompanyID(String companyID);
    ResponseEntity<Object> addFleet(FleetRequest fleet);
    ResponseEntity<Object> updateFleet(FleetRequest fleet, String fleetID);
    ResponseEntity<Object> deleteFleet(String fleetID);
    ResponseEntity<Object> deleteFleetsByCompanyID(String companyID);
    void deleteFleetByCompanyID(String companyID);
}
