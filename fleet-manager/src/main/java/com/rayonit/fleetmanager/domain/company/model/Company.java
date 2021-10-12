package com.rayonit.fleetmanager.domain.company.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company {
    @Id
    private String companyID;
    private String userID;
    private String companyName;

    public Company() {
    }

    public Company(String companyID, String userID, String companyName) {
        this.companyID = companyID;
        this.userID = userID;
        this.companyName = companyName;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getUserID() {
        return userID;
    }

    public String getCompanyName() {
        return companyName;
    }
}
