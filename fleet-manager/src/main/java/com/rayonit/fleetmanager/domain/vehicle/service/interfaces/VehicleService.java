package com.rayonit.fleetmanager.domain.vehicle.service.interfaces;


import com.rayonit.fleetmanager.domain.driver.dto.DriverRequest;
import com.rayonit.fleetmanager.domain.vehicle.dto.VehicleRequest;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {
    ResponseEntity<Object> getAllVehicles();
    ResponseEntity<Object> addVehicle(VehicleRequest vehicle);
    ResponseEntity<Object> updateVehicle(VehicleRequest vehicle, String vehicleID);
    ResponseEntity<Object> deleteVehicle(String vehicleId);
    ResponseEntity<Object> getAllVehiclesByFleetID(String fleetID);
    ResponseEntity<Object> getAllVehiclesByUserID(String userID);
    List<Vehicle> getAllVehiclesByCompanyID(String companyID);
    ResponseEntity<Object> assignDriver(String driverID, String vehicleID);
    void deleteVehicleByFleetID(String fleetID);
    void removeDriver(String driverID);
    ResponseEntity<Object> fixCar(String vehicleID);
    ResponseEntity<Object> refillCar(String vehicleID);
    ResponseEntity<Object> unAssignDriver(String vehicleID);
}
