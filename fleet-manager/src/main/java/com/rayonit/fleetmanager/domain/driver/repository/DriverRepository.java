package com.rayonit.fleetmanager.domain.driver.repository;

import com.rayonit.fleetmanager.domain.driver.model.Driver;
import com.rayonit.fleetmanager.domain.fleet.model.Fleet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
    List<Driver> findAllByCompanyID(String companyID);
}
