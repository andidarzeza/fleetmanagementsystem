package com.rayonit.fleetmanager.domain.company.controller;

import com.rayonit.fleetmanager.domain.company.dto.CompanyRequest;
import com.rayonit.fleetmanager.domain.company.dto.CompanyResponse;
import com.rayonit.fleetmanager.domain.company.model.Company;
import com.rayonit.fleetmanager.domain.company.repository.CompanyRepository;
import com.rayonit.fleetmanager.domain.company.service.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.print.attribute.standard.Media;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addCompany(@RequestBody CompanyRequest companyRequest){
        return this.companyService.addCompany(companyRequest);
    }

    @PutMapping("/update/{companyID}")
    public ResponseEntity<Object> updateCompany(@RequestBody CompanyRequest companyRequest, @PathVariable String companyID){
        return companyService.updateCompany(companyRequest, companyID);
    }

    @DeleteMapping("/delete/{companyID}")
    public ResponseEntity<Object> deleteCompany(@PathVariable String companyID){
        return companyService.deleteCompany(companyID);
    }

    @GetMapping(value = "/all/{userID:.+}")
    public ResponseEntity<Object> getCompaniesByUserID(@PathVariable String userID){
        return companyService.getCompaniesByUserID(userID);
    }

    @GetMapping("/companyNames/{userID:.+}")
    public ResponseEntity<Object> getAllCompanynames(@PathVariable String userID){
        return companyService.getAllCompanyNames(userID);
    }

}

