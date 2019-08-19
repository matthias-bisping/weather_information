package de.mbis.demo.weather.service.model;

import java.io.Serializable;

public class ServerStatus implements Serializable {
    private String status;
    private String session;

    public ServerStatus(String status, String session) {
        this.status = status;
        this.session = session;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public static ServerStatus ok(String session) {
        return new ServerStatus("OK", session);
    }
}
