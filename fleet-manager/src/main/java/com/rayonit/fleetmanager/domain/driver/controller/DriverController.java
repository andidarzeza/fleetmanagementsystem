package com.rayonit.fleetmanager.domain.driver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rayonit.fleetmanager.domain.driver.dto.DriverRequest;
import com.rayonit.fleetmanager.domain.driver.model.Driver;
import com.rayonit.fleetmanager.domain.driver.service.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/drivers")
@CrossOrigin(origins = "http://localhost:4200")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllDrivers(){
        return driverService.getAllDrivers();
    }

    @GetMapping("/getByCompanyID/{companyID}")
    public ResponseEntity<Object> getAllByCompanyID(@PathVariable String companyID){
        return driverService.getAllDriversByCompanyID(companyID);
    }

    @GetMapping("{driverID}")
    public ResponseEntity<Object> getDriver(@PathVariable String driverID) {
        return driverService.getDriver(driverID);
    }

    @DeleteMapping("/delete/{driverID}")
    public ResponseEntity<Object> removeDriver(@PathVariable String driverID){
        return driverService.removeDriver(driverID);
    }

    @PutMapping("/update/{vehicleID}")
    public ResponseEntity<Object> updateDriver(@RequestBody DriverRequest driverRequest, @PathVariable String vehicleID){
        return driverService.updateDriver(driverRequest, vehicleID);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addDriver(@RequestParam("driver") String driver, @RequestParam("profilePhoto") MultipartFile file){
        System.out.println("Driver");
        return driverService.addDriver(driver, file);
    }

    @GetMapping("/getUnassignedDrivers/{companyID}")
    public ResponseEntity<Object> getUnassignedDrivers(@PathVariable String companyID){
        return driverService.getUnassignedDrivers(companyID);
    }


}
