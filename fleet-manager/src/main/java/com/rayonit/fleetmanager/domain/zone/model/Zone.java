package com.rayonit.fleetmanager.domain.zone.model;

import com.rayonit.fleetmanager.general.classes.Position;
import com.rayonit.fleetmanager.models.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
public class Zone {
    @Id
    private String zoneID;
    private List<Position> coordinates;

    public Zone() {
    }

    public Zone(String zoneID, List<Position> coordinates) {
        this.zoneID = zoneID;
        this.coordinates = coordinates;
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
