package com.rayonit.fleetmanager.domain.history.repository;

import com.rayonit.fleetmanager.domain.history.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {
    List<History> getAllByUserID(String userID);
}
