package com.rayonit.fleetmanager.general.classes;

import javax.security.auth.Subject;
import java.security.Principal;

public class StompPrincipal implements Principal {
    private String name;

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
