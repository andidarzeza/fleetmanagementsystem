package com.rayonit.fleetmanager.domain.user.dto;

public class UserRequest {
    private String username;
    private String password;

    public UserRequest() {

    }

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
