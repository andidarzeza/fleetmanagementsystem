package com.rayonit.fleetmanager.domain.zone.repository;

import com.rayonit.fleetmanager.domain.zone.model.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends MongoRepository<Zone, String> {

}
