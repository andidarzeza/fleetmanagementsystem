package com.rayonit.fleetmanager.domain.fleet.controller;

import com.rayonit.fleetmanager.domain.fleet.dto.FleetRequest;
import com.rayonit.fleetmanager.domain.fleet.model.Fleet;
import com.rayonit.fleetmanager.domain.fleet.service.interfaces.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fleets")
@CrossOrigin(origins = "http://localhost:4200")
public class FleetController {

    @Autowired
    private FleetService fleetService;

    @GetMapping("/{companyID}")
    public ResponseEntity<Object> getAllFleetsByCompanyID(@PathVariable String companyID){
        return fleetService.getAllFleetsByCompanyID(companyID);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addFleet(@RequestBody FleetRequest fleetRequest){
        return fleetService.addFleet(fleetRequest);
    }

    @PutMapping("/update/{fleetID}")
    public ResponseEntity<Object> updateFleet(@RequestBody FleetRequest fleetRequest, @PathVariable String fleetID){
        return fleetService.updateFleet(fleetRequest, fleetID);
    }

    @DeleteMapping("/delete/{fleetID}")
    public ResponseEntity<Object> deleteFleet(@PathVariable String fleetID){
        return fleetService.deleteFleet(fleetID);
    }

    @DeleteMapping("/deleteByCompany/{companyID}")
    public ResponseEntity<Object> deleteFleetsByCompanyID(@PathVariable String companyID){
        return fleetService.deleteFleetsByCompanyID(companyID);
    }
}
