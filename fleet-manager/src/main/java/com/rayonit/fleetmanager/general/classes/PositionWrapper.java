package com.rayonit.fleetmanager.general.classes;

import com.rayonit.fleetmanager.domain.company.model.CompanyInfo;
import com.rayonit.fleetmanager.domain.fleet.model.FleetInfo;

public class PositionWrapper {
    private String vehicleID;
    private CompanyInfo companyInfo;
    private FleetInfo fleetInfo;
    private Double x;
    private Double y;

    public PositionWrapper() {
    }

    public PositionWrapper(String vehicleID, CompanyInfo companyInfo, FleetInfo fleetInfo, Double x, Double y) {
        this.vehicleID = vehicleID;
        this.companyInfo = companyInfo;
        this.fleetInfo = fleetInfo;
        this.x = x;
        this.y = y;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
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

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
