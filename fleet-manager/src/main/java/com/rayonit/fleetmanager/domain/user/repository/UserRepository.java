package com.rayonit.fleetmanager.domain.user.repository;

import com.rayonit.fleetmanager.domain.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
