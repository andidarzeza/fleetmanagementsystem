package com.rayonit.fleetmanager.general.classes;

import java.util.List;

public class Route {
    private List<Position> route;

    public Route() {
    }

    public Route(List<Position> route) {
        this.route = route;
    }

    public List<Position> getRoute() {
        return route;
    }

    public void setRoute(List<Position> route) {
        this.route = route;
    }
}
