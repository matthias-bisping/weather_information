package de.mbis.demo.weather.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Updater Properties")
public class Properties {
    @ApiModelProperty(notes = "City to update", example = "Oelde")
    private String city;
    @ApiModelProperty(notes = "Update interval", example = "60")
    private int interval;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
