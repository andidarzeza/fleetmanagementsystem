package com.rayonit.fleetmanager.domain.user.dto;

import com.rayonit.fleetmanager.domain.user.model.Role;

public class UserResponse {
    private String username;
    private Role role;

    public UserResponse(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
