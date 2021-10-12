package com.rayonit.fleetmanager.domain.driver.dto;

import java.util.Date;

public class DriverResponse {
    private String driverID;
    private String companyID;
    private String firstName;
    private String lastName;
    private int driverAge;
    private String driverAddress;
    private Date vehicleAssignedFrom;
    private String profilePhoto;

    public DriverResponse(String driverID, String companyID, String firstName, String lastName, int driverAge, String driverAddress, Date vehicleAssignedFrom, String profilePhoto) {
        this.driverID = driverID;
        this.companyID = companyID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverAge = driverAge;
        this.driverAddress = driverAddress;
        this.vehicleAssignedFrom = vehicleAssignedFrom;
        this.profilePhoto = profilePhoto;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
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

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
