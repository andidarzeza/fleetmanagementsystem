package com.rayonit.fleetmanager.domain.company.service.implementations;

import com.rayonit.fleetmanager.domain.company.dto.CompanyRequest;
import com.rayonit.fleetmanager.domain.company.dto.CompanyResponse;
import com.rayonit.fleetmanager.domain.company.model.Company;
import com.rayonit.fleetmanager.domain.company.repository.CompanyRepository;
import com.rayonit.fleetmanager.domain.company.service.interfaces.CompanyService;
import com.rayonit.fleetmanager.domain.driver.service.interfaces.DriverService;
import com.rayonit.fleetmanager.domain.fleet.model.Fleet;
import com.rayonit.fleetmanager.domain.fleet.service.interfaces.FleetService;
import com.rayonit.fleetmanager.domain.history.dto.HistoryRequest;
import com.rayonit.fleetmanager.domain.history.model.enums.Action;
import com.rayonit.fleetmanager.domain.history.model.enums.Departments;
import com.rayonit.fleetmanager.domain.history.service.interfaces.HistoryService;
import com.rayonit.fleetmanager.domain.notification.service.interfaces.NotificationService;
import com.rayonit.fleetmanager.general.classes.Message;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FleetService fleetService;

    @Autowired
    private DriverService driverService;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private Converter<Company, CompanyRequest, CompanyResponse> converter;

    @Override
    public ResponseEntity<Object> getAllCompanies() {
        return new ResponseEntity<>(converter.convertToResponseList(companyRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addCompany(CompanyRequest companyRequest) {
        if(companyRequest != null) {
            Company company = converter.convertToModel(companyRequest);
            companyRepository.save(company);
            historyService.addHistory(new HistoryRequest(
                    companyRequest.getUserID(),
                    Departments.COMPANIES,
                    new Date(),
                    Action.CREATED_COMPANY
            ));
            return new ResponseEntity<>(converter.convertToResponse(company), HttpStatus.OK);
        }
        else return new ResponseEntity<>(new Message("No Object of type Company was provided!"), HttpStatus.NOT_FOUND);

    }


    @Override
    public ResponseEntity<Object> updateCompany(CompanyRequest companyRequest, String companyID) {
        if (companyRequest == null){
            return new ResponseEntity<>(new Message("No Object of type Company was provided!"), HttpStatus.NOT_FOUND);
        }else{
            Optional<Company> companyOptional = companyRepository.findById(companyID);
            if (companyOptional.isPresent()){
                Company company = converter.convertToModel(companyRequest);
                company.setCompanyID(companyID);
                companyRepository.save(company);
                historyService.addHistory(new HistoryRequest(
                        companyRequest.getUserID(),
                        Departments.COMPANIES,
                        new Date(),
                        Action.UPDATED_COMPANY
                ));
                return new ResponseEntity<>(converter.convertToResponse(company), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new Message("Could not find Company with the given ID: " + companyID), HttpStatus.NOT_FOUND);
            }
        }
    }


    @Override
    public ResponseEntity<Object> deleteCompany(String companyID) {
        Optional<Company> companyOptional = companyRepository.findById(companyID);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            companyRepository.delete(company);
            this.deleteAllCompanyResources(companyID);
            historyService.addHistory(new HistoryRequest(
                    company.getUserID(),
                    Departments.COMPANIES,
                    new Date(),
                    Action.DELETED_COMPANY
            ));
            return new ResponseEntity<>(converter.convertToResponse(company), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("Could not find Company with the given ID: " + companyID), HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<Object> getCompaniesByUserID(String userID) {
        return new ResponseEntity<>(converter.convertToResponseList(companyRepository.findAllByUserID(userID)), HttpStatus.OK);
    }

    @Override
    public Company getCompany(String companyID){
        return companyRepository.findById(companyID).orElse(null);
    }

    @Override
    public ResponseEntity<Object> getAllCompanyNames(String userID) {
        List<String> companyNames = companyRepository.findAllByUserID(userID)
                .stream()
                .map(company -> company.getCompanyName())
                .collect(Collectors.toList());
        return new ResponseEntity<>(companyNames, HttpStatus.OK);
    }


    //This method will serve the purpose of deleting all the fleets, vehicles and driver associated with the given company,
    //the reason being that those resources do not exist if the corresponding company is deleted!
    @Override
    public void deleteAllCompanyResources(String companyID) {
        this.fleetService.deleteFleetByCompanyID(companyID);
        this.driverService.deleteDriverByCompanyID(companyID);
    }

    @Override
    public List<Company> getUserCompanies(String userID) {
        return companyRepository.findAllByUserID(userID);
    }
}
