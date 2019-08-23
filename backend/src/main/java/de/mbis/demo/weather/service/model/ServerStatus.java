package de.mbis.demo.weather.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Server and session status")
public class ServerStatus implements Serializable {
    @ApiModelProperty(notes = "Server status", example = "OK")
    private String status;
    @ApiModelProperty(notes = "Current session id", example = "958D62B92A2AA484B72BEB525296DCEE")
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
