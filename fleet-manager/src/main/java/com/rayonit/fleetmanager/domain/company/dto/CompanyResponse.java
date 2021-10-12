package com.rayonit.fleetmanager.domain.company.dto;

public class CompanyResponse {
    private String companyID;
    private String userID;
    private String companyName;

    public CompanyResponse() {
    }

    public CompanyResponse(String companyID, String userID, String companyName) {
        this.companyID = companyID;
        this.userID = userID;
        this.companyName = companyName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
