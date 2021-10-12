package com.rayonit.fleetmanager.domain.fleet.dto;

public class FleetResponse {
    private String fleetID;
    private String companyID;
    private String fleetName;

    public FleetResponse(String fleetID, String companyID, String fleetName) {
        this.fleetID = fleetID;
        this.companyID = companyID;
        this.fleetName = fleetName;
    }

    public String getFleetID() {
        return fleetID;
    }

    public void setFleetID(String fleetID) {
        this.fleetID = fleetID;
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
