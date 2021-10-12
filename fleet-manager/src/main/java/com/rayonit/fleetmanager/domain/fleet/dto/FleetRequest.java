package com.rayonit.fleetmanager.domain.fleet.dto;

public class FleetRequest {
    private String companyID;
    private String fleetName;

    public FleetRequest() {
    }

    public FleetRequest(String companyID, String fleetName) {
        this.companyID = companyID;
        this.fleetName = fleetName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }
}
