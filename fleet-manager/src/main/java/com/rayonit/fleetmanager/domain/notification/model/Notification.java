package com.rayonit.fleetmanager.domain.notification.model;

import com.rayonit.fleetmanager.domain.company.model.CompanyInfo;
import com.rayonit.fleetmanager.domain.fleet.model.FleetInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
public class Notification {
    @Id
    private String notificationID;
    private String userID;
    private String message;
    private Date date;
    private CompanyInfo companyInfo;
    private FleetInfo fleetInfo;
    private String vehicleID;
    private String vehicleName;

    public Notification() {
    }

    public Notification(String notificationID, String userID, String message, CompanyInfo companyInfo, FleetInfo fleetInfo, String vehicleID, String vehicleName) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.message = message;
        this.date = new Date();
        this.companyInfo = companyInfo;
        this.fleetInfo = fleetInfo;
        this.vehicleID = vehicleID;
        this.vehicleName = vehicleName;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
