package com.rayonit.fleetmanager.domain.vehicle.repository;

import com.rayonit.fleetmanager.domain.driver.model.Driver;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    List<Vehicle> findAllByFleetInfoFleetID(String fleetID);
    List<Vehicle> findAllByUserID(String userID);
    List<Vehicle> findAllByDriverIDExists();
    List<Vehicle> findAllByCompanyInfoCompanyID(String companyID);
    Optional<Vehicle> findByDriverID(String driverID);
}
