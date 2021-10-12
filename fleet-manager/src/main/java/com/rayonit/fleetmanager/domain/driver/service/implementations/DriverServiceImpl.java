package com.rayonit.fleetmanager.domain.driver.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rayonit.fleetmanager.domain.company.service.interfaces.CompanyService;
import com.rayonit.fleetmanager.domain.driver.dto.DriverRequest;
import com.rayonit.fleetmanager.domain.driver.dto.DriverResponse;
import com.rayonit.fleetmanager.domain.driver.model.Driver;
import com.rayonit.fleetmanager.domain.driver.repository.DriverRepository;
import com.rayonit.fleetmanager.domain.driver.service.interfaces.DriverService;
import com.rayonit.fleetmanager.domain.driver.service.interfaces.ImageConverter;
import com.rayonit.fleetmanager.domain.history.dto.HistoryRequest;
import com.rayonit.fleetmanager.domain.history.model.enums.Action;
import com.rayonit.fleetmanager.domain.history.model.enums.Departments;
import com.rayonit.fleetmanager.domain.history.service.interfaces.HistoryService;
import com.rayonit.fleetmanager.domain.vehicle.model.Vehicle;
import com.rayonit.fleetmanager.domain.vehicle.service.interfaces.VehicleService;
import com.rayonit.fleetmanager.general.classes.Message;
import com.rayonit.fleetmanager.general.interfaces.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository repository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private Converter<Driver, DriverRequest, DriverResponse> converter;

    @Autowired
    private ImageConverter imageConverter;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public ResponseEntity<Object> getAllDrivers() {
        return new ResponseEntity<>(converter.convertToResponseList(repository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> removeDriver(String driverID) {
        Optional<Driver> driverOptional = repository.findById(driverID);
        if (driverOptional.isPresent()){
            Driver driver = driverOptional.get();
            repository.delete(driver);
            vehicleService.removeDriver(driverID);
            historyService.addHistory(new HistoryRequest(
                    companyService.getCompany(driver.getCompanyID()).getUserID(),
                    Departments.DRIVERS,
                    new Date(),
                    Action.DELETED_DRIVER
            ));
            return new ResponseEntity<>(converter.convertToResponse(driver), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("Could not find Driver with the given ID:" + driverID), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getDriver(String driverID) {
        Optional<Driver> driverOptional = repository.findById(driverID);
        if (driverOptional.isPresent()){
            Driver driver = driverOptional.get();
            return new ResponseEntity<>(converter.convertToResponse(driver), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("Could not find Driver with the given ID: " + driverID), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> updateDriver(DriverRequest driverRequest, String driverID) {
        if (driverRequest == null){
            return new ResponseEntity<>(new Message("No Object of Driver was provided!"), HttpStatus.NOT_FOUND);
        }else{
            Optional<Driver> driverOptional = repository.findById(driverID);
            if (driverOptional.isPresent()) {
                Driver driver = converter.convertToModel(driverRequest);
                driver.setDriverID(driverID);
                repository.save(driver);
                historyService.addHistory(new HistoryRequest(
                        companyService.getCompany(driver.getCompanyID()).getUserID(),
                        Departments.DRIVERS,
                        new Date(),
                        Action.UPDATED_DRIVER
                ));
                return new ResponseEntity<>(converter.convertToResponse(driver), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new Message("Could not find Driver with the given ID: " + driverID), HttpStatus.NOT_FOUND);
            }
        }
    }

    @Override
    public ResponseEntity<Object> addDriver(String driverString, MultipartFile file) {
        try {
            DriverRequest driverRequest = new ObjectMapper().readValue(driverString, DriverRequest.class);
            if (driverRequest == null) return new ResponseEntity<>(new Message("No Object of Driver was provided!"), HttpStatus.NOT_FOUND);
            Driver driver = converter.convertToModel(driverRequest);
            driver.setProfilePhoto(imageConverter.convert(file));
            repository.save(driver);
            historyService.addHistory(new HistoryRequest(
                    companyService.getCompany(driver.getCompanyID()).getUserID(),
                    Departments.DRIVERS,
                    new Date(),
                    Action.CREATED_DRIVER
            ));
            return new ResponseEntity<>(converter.convertToResponse(driver), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("An error occurred!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getAllDriversByCompanyID(String companyID) {
        return new ResponseEntity<>(converter.convertToResponseList(repository.findAllByCompanyID(companyID)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getUnassignedDrivers(String companyID) {
        List<Driver> driverList = repository.findAllByCompanyID(companyID);
        List<Vehicle> vehicles = vehicleService.getAllVehiclesByCompanyID(companyID);
        List<Driver> responseList = new ArrayList<>();
        driverList.forEach(driver -> {
            AtomicBoolean unassigned = new AtomicBoolean(true);
            vehicles.forEach(vehicle -> {
                if(vehicle != null && driver != null){
                    if(vehicle.getDriverID().equals(driver.getDriverID())){
                        unassigned.set(false);
                    }
                }

            });
            if (unassigned.get()){
                responseList.add(driver);
            }
        });
        return new ResponseEntity<>(converter.convertToResponseList(responseList), HttpStatus.OK);
    }

    @Override
    public void deleteDriverByCompanyID(String companyID) {
        List<Driver> driversToBeDeleted = repository.findAllByCompanyID(companyID);
        repository.deleteAll(driversToBeDeleted);
    }
}
