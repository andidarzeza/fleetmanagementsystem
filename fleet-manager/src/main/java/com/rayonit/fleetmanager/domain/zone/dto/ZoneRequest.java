package com.rayonit.fleetmanager.domain.zone.dto;

import com.rayonit.fleetmanager.general.classes.Position;

import java.util.List;

public class ZoneRequest {
    private List<Position> coordinates;

    public ZoneRequest(List<Position> coordinates) {
        this.coordinates = coordinates;
    }

    public ZoneRequest() {
    }

    public List<Position> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Position> coordinates) {
        this.coordinates = coordinates;
    }
}
