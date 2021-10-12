package com.rayonit.fleetmanager.domain.company.dto;

public class CompanyRequest {
    private String userID;
    private String companyName;

    public CompanyRequest() {
    }

    public CompanyRequest(String userID, String companyName) {
        this.userID = userID;
        this.companyName = companyName;
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
