package com.rayonit.fleetmanager.domain.fleet.repository;

import com.rayonit.fleetmanager.domain.fleet.model.Fleet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FleetRepository extends MongoRepository<Fleet, String> {
    List<Fleet> findAllByCompanyID(String companyID);
    void deleteAllByCompanyID(String companyID);
}
