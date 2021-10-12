package com.rayonit.fleetmanager.domain.history.dto;

import com.rayonit.fleetmanager.domain.history.model.enums.Action;
import com.rayonit.fleetmanager.domain.history.model.enums.Departments;

import java.util.Date;

public class HistoryRequest {
    private String userID;
    private Departments where;
    private Date date;
    private Action action;

    public HistoryRequest(String userID, Departments where, Date date, Action action) {
        this.userID = userID;
        this.where = where;
        this.date = date;
        this.action = action;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Departments getWhere() {
        return where;
    }

    public void setWhere(Departments where) {
        this.where = where;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
