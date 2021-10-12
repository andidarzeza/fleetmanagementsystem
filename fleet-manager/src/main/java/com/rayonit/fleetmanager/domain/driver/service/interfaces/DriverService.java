package com.rayonit.fleetmanager.domain.driver.service.interfaces;

import com.rayonit.fleetmanager.domain.driver.dto.DriverRequest;
import com.rayonit.fleetmanager.domain.driver.model.Driver;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DriverService {
    ResponseEntity<Object> getAllDrivers();
    ResponseEntity<Object> removeDriver(String driverID);
    ResponseEntity<Object> updateDriver(DriverRequest driver, String driverID);
    ResponseEntity<Object> addDriver(String driver, MultipartFile file);
    ResponseEntity<Object> getDriver(String driverID);
    ResponseEntity<Object> getAllDriversByCompanyID(String companyID);
    ResponseEntity<Object> getUnassignedDrivers(String companyID);
    void deleteDriverByCompanyID(String companyID);
}
