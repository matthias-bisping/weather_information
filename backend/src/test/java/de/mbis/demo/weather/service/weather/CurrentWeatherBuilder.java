package de.mbis.demo.weather.service.weather;

import de.mbis.demo.weather.service.model.CurrentWeather;
import de.mbis.demo.weather.service.model.Main;
import de.mbis.demo.weather.service.model.Weather;
import de.mbis.demo.weather.service.model.Wind;

import java.util.Collections;

class CurrentWeatherBuilder {
    private float temp = 288.15f;
    private float pressure = 1019.0f;
    private float humidity = 72.0f;
    private int weatherId = 800;
    private String main = "Clear";
    private String description = "clear sky";
    private String icon = "01d";
    private float speed = 3.1f;
    private int deg = 220;
    private int id = 2857943;
    private String name = "Oelde";
    private boolean withWind = true;
    private boolean withMain = true;
    private boolean withWeather = true;

    CurrentWeatherBuilder withTemp(float temp) {
        this.temp = temp;
        return this;
    }

    CurrentWeatherBuilder withPressure(float pressure) {
        this.pressure = pressure;
        return this;
    }

    CurrentWeatherBuilder withHumidity(float humidity) {
        this.humidity = humidity;
        return this;
    }

    CurrentWeatherBuilder withWeatherId(int weatherId) {
        this.weatherId = weatherId;
        return this;
    }

    CurrentWeatherBuilder withMain(String main) {
        this.main = main;
        return this;
    }

    CurrentWeatherBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    CurrentWeatherBuilder withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    CurrentWeatherBuilder withSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    CurrentWeatherBuilder withDeg(int deg) {
        this.deg = deg;
        return this;
    }

    CurrentWeatherBuilder withId(int id) {
        this.id = id;
        return this;
    }

    CurrentWeatherBuilder withName(String name) {
        this.name = name;
        return this;
    }

    static CurrentWeatherBuilder create() {
        return new CurrentWeatherBuilder();
    }

    CurrentWeatherBuilder withoutWind() {
        this.withWind = false;
        return this;
    }

    CurrentWeatherBuilder withoutMain() {
        this.withMain = false;
        return this;
    }

    CurrentWeatherBuilder withoutWeather() {
        this.withWeather = false;
        return this;
    }

    CurrentWeather build() {
        CurrentWeather result = new CurrentWeather();
        result.setId(this.id);
        result.setName(this.name);

        if(withWind) {
            Wind wind = new Wind();
            wind.setDeg(this.deg);
            wind.setSpeed(this.speed);
            result.setWind(wind);
        }

        if(withMain) {
            Main main = new Main();
            main.setTemp(this.temp);
            main.setHumidity(this.humidity);
            main.setPressure(this.pressure);
            result.setMain(main);
        }

        if(withWeather) {
            Weather weather = new Weather();
            weather.setDescription(this.description);
            weather.setIcon(this.icon);
            weather.setId(this.weatherId);
            weather.setMain(this.main);
            result.setWeathers(Collections.singletonList(weather));
        }

        return result;
    }

    CurrentWeatherBuilder but() {
        return CurrentWeatherBuilder
                .create()
                .withDeg(this.deg)
                .withDescription(this.description)
                .withHumidity(this.humidity)
                .withIcon(this.icon)
                .withId(this.id)
                .withMain(this.main)
                .withName(this.name)
                .withPressure(this.pressure)
                .withSpeed(this.speed)
                .withTemp(this.temp)
                .withWeatherId(this.weatherId);
    }

}