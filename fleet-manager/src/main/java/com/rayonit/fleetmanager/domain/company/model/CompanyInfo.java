package com.rayonit.fleetmanager.domain.company.model;

public class CompanyInfo {
    private String companyID;
    private String companyName;

    public CompanyInfo(String companyID, String companyName) {
        this.companyID = companyID;
        this.companyName = companyName;
    }

    public CompanyInfo() {
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
