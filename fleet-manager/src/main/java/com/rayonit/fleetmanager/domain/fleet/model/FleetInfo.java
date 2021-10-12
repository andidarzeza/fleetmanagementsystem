package com.rayonit.fleetmanager.domain.fleet.model;

public class FleetInfo {
    private String fleetID;
    private String fleetName;

    public FleetInfo() {
    }

    public FleetInfo(String fleetID, String fleetName) {
        this.fleetID = fleetID;
        this.fleetName = fleetName;
    }

    public String getFleetID() {
        return fleetID;
    }

    public void setFleetID(String fleetID) {
        this.fleetID = fleetID;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }
}
