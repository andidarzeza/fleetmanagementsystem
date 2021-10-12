package com.rayonit.fleetmanager.models;

import java.util.List;
import java.util.UUID;

public class Zone {
    private UUID id;
    private List<Point> points;

    public Zone(List<Point> points) {
        this.id = UUID.randomUUID();
        this.points = points;
    }

    public UUID getId() {
        return id;
    }

    public List<Point> getPoints() {
        return points;
    }
}

