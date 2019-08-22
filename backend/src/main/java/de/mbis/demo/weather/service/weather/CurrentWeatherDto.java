package de.mbis.demo.weather.service.weather;

import de.mbis.demo.weather.service.model.CurrentWeather;
import de.mbis.demo.weather.service.model.Main;
import de.mbis.demo.weather.service.model.Weather;
import de.mbis.demo.weather.service.model.Wind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class CurrentWeatherDto {
    private String messageId;
    private String messageTimestamp;

    private int id;
    private String name;
    private String main;
    private String description;
    private String iconUrl;
    private float temperature;
    private float pressure;
    private float humidity;
    private float windSpeed;
    private int windDegree;

    public CurrentWeatherDto() {
        messageId = UUID.randomUUID().toString();
        messageTimestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static CurrentWeatherDto toDto(CurrentWeather currentWeather, String iconUrlTemplate) {
        if(currentWeather == null) return null;

        CurrentWeatherDto result = new CurrentWeatherDto();
        result.setId(currentWeather.getId());
        result.setName(currentWeather.getName());

        if(currentWeather.getWeathers() != null && currentWeather.getWeathers().size() > 0) {
            Weather weather = currentWeather.getWeathers().get(0);
            result.setDescription(weather.getDescription());
            result.setMain(weather.getMain());

            if(iconUrlTemplate != null)
                result.setIconUrl(String.format(iconUrlTemplate, weather.getIcon()));
        }

        Main main = currentWeather.getMain();
        if(main != null) {
            result.setTemperature(main.getTemp());
            result.setPressure(main.getPressure());
            result.setHumidity(main.getHumidity());
        }

        Wind wind = currentWeather.getWind();
        if(wind != null) {
            result.setWindSpeed(wind.getSpeed());
            result.setWindDegree(wind.getDeg());
        }

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(int windDegree) {
        this.windDegree = windDegree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessageTimestamp() {
        return messageTimestamp;
    }
}
