package com.rayonit.fleetmanager.domain.fleet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Fleet {
    @Id
    private String fleetID;
    private String companyID;
    private String fleetName;

    public Fleet(String fleetID, String companyID, String fleetName) {
        this.fleetID = fleetID;
        this.companyID = companyID;
        this.fleetName = fleetName;
    }

    public void setFleetID(String fleetID) {
        this.fleetID = fleetID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public String getFleetID() {
        return fleetID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getFleetName() {
        return fleetName;
    }
}
