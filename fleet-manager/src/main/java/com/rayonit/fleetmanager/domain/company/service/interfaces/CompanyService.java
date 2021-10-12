package com.rayonit.fleetmanager.domain.company.service.interfaces;


import com.rayonit.fleetmanager.domain.company.dto.CompanyRequest;
import com.rayonit.fleetmanager.domain.company.model.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {
    ResponseEntity<Object> getAllCompanies();
    ResponseEntity<Object> addCompany(CompanyRequest company);
    ResponseEntity<Object> updateCompany(CompanyRequest company, String companyID);
    ResponseEntity<Object> deleteCompany(String companyID);
    ResponseEntity<Object> getCompaniesByUserID(String userID);
    Company getCompany(String companyID);
    ResponseEntity<Object> getAllCompanyNames(String userID);
    void deleteAllCompanyResources(String companyID);
    List<Company> getUserCompanies(String userID);
}
