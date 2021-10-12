package com.rayonit.fleetmanager.domain.driver.dto;

import com.rayonit.fleetmanager.domain.driver.model.Driver;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class DriverRequest {
    private String companyID;
    private String firstName;
    private String lastName;
    private int driverAge;
    private String driverAddress;
    private Date vehicleAssignedFrom;

    public DriverRequest(){

    }

    public DriverRequest(String companyID, String firstName, String lastName, int driverAge, String driverAddress, Date vehicleAssignedFrom) {
        this.companyID = companyID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverAge = driverAge;
        this.driverAddress = driverAddress;
        this.vehicleAssignedFrom = vehicleAssignedFrom;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public Date getVehicleAssignedFrom() {
        return vehicleAssignedFrom;
    }

    public void setVehicleAssignedFrom(Date vehicleAssignedFrom) {
        this.vehicleAssignedFrom = vehicleAssignedFrom;
    }
}
