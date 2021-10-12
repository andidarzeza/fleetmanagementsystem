package com.rayonit.fleetmanager.domain.vehicle.controller;

import com.rayonit.fleetmanager.domain.vehicle.dto.VehicleRequest;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import com.rayonit.fleetmanager.domain.vehicle.service.interfaces.VehicleService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }


    @GetMapping("/{fleetID}")
    public ResponseEntity<Object> getAllVehiclesByFleetID(@PathVariable String fleetID){
        return vehicleService.getAllVehiclesByFleetID(fleetID);
    }

    @GetMapping("/user/{userID:.+}")
    public ResponseEntity<Object> getAllVehiclesByUserID(@PathVariable String userID){
        return vehicleService.getAllVehiclesByUserID(userID);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addVehicle(@RequestBody VehicleRequest vehicleRequest){
        return vehicleService.addVehicle(vehicleRequest);
    }

    @PutMapping("/update/{vehicleID}")
    public ResponseEntity<Object> updateVehicle(@RequestBody VehicleRequest vehicleRequest, @PathVariable String vehicleID){
        return vehicleService.updateVehicle(vehicleRequest, vehicleID);
    }

    @DeleteMapping("/delete/{vehicleID}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable String vehicleID){
        return vehicleService.deleteVehicle(vehicleID);
    }

    @PutMapping("/updateVehicleDriver/{vehicleID}")
    public ResponseEntity<Object> updateVehicleDriver(@RequestParam("driverID") String driverID, @PathVariable String vehicleID){
        return vehicleService.assignDriver(driverID, vehicleID);
    }

    @PutMapping("/fix/{vehicleID}")
    public ResponseEntity<Object> fixCar(@PathVariable String vehicleID){
        return vehicleService.fixCar(vehicleID);
    }

    @PutMapping("/refill/{vehicleID}")
    public ResponseEntity<Object> refillCar(@PathVariable String vehicleID){
        return vehicleService.refillCar(vehicleID);
    }

    @PutMapping("/unassign/{vehicleID}")
    public ResponseEntity<Object> unAssignDriver(@PathVariable String vehicleID){
        return this.vehicleService.unAssignDriver(vehicleID);
    }

}
