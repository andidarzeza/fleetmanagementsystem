package com.rayonit.fleetmanager.domain.company.repository;

import com.rayonit.fleetmanager.domain.company.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    List<Company> findAllByUserID(String userID);
    List<Company> findAllByCompanyNameStartingWith(String companyName);
}
