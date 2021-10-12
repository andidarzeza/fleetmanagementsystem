package com.rayonit.fleetmanager.domain.vehicle.model;

import com.rayonit.fleetmanager.domain.company.model.CompanyInfo;
import com.rayonit.fleetmanager.domain.fleet.model.FleetInfo;
import com.rayonit.fleetmanager.general.classes.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vehicle {
    @Id
    private String vehicleID;
    private String userID;
    private CompanyInfo companyInfo;
    private FleetInfo fleetInfo;
    private String driverID;
    private String vehicleName;
    private Type type;
    private Double fuel;
    private int speed;
    private boolean isBroken = false;
    private Position position;

    public Vehicle(String vehicleID, String userID, CompanyInfo companyInfo, FleetInfo fleetInfo, String driverID, String vehicleName, Type type, Double fuel, int speed, Position position) {
        this.vehicleID = vehicleID;
        this.userID = userID;
        this.companyInfo = companyInfo;
        this.fleetInfo = fleetInfo;
        this.driverID = driverID;
        this.vehicleName = vehicleName;
        this.type = type;
        this.fuel = fuel;
        this.speed = speed;
        this.position = position;
    }

    public Boolean lowFuel(){
        return this.getFuel() < 2;
    }

    public Boolean isEligible() {
        return (!this.driverID.equals("") && this.driverID != null && (!this.lowFuel()) && !this.isBroken());
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public FleetInfo getFleetInfo() {
        return fleetInfo;
    }

    public void setFleetInfo(FleetInfo fleetInfo) {
        this.fleetInfo = fleetInfo;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getFuel() {
        return fuel;
    }

    public void setFuel(Double fuel) {
        this.fuel = fuel;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }
}
