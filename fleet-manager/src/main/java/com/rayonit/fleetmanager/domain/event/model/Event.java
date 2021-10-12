package com.rayonit.fleetmanager.domain.event.model;

import com.rayonit.fleetmanager.general.classes.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
public class Event {
    @Id
    private String eventID;
    private String userID;
    private String vehicleId;
    private Position position;
    private String eventName;

    public Event() {
    }

    public Event(String eventID, String userID, String vehicleId, Position position, String eventName) {
        this.eventID = eventID;
        this.userID = userID;
        this.vehicleId = vehicleId;
        this.position = position;
        this.eventName = eventName;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
