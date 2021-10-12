package com.rayonit.fleetmanager.domain.zone.dto;

import com.rayonit.fleetmanager.general.classes.Position;

import java.util.List;

public class ZoneResponse {
    private String zoneID;
    private List<Position> coordinates;

    public ZoneResponse(String zoneID, List<Position> coordinates) {
        this.zoneID = zoneID;
        this.coordinates = coordinates;
    }

    public ZoneResponse() {
    }

    public String getZoneID() {
        return zoneID;
    }

    public void setZoneID(String zoneID) {
        this.zoneID = zoneID;
    }

    public List<Position> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Position> coordinates) {
        this.coordinates = coordinates;
    }
}
