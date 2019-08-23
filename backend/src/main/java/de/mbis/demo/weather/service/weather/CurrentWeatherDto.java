package de.mbis.demo.weather.service.weather;

import de.mbis.demo.weather.service.model.CurrentWeather;
import de.mbis.demo.weather.service.model.Main;
import de.mbis.demo.weather.service.model.Weather;
import de.mbis.demo.weather.service.model.Wind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@ApiModel(description = "Current weather information")
class CurrentWeatherDto {
    @ApiModelProperty(notes = "UUID to identify message", example = "799a211f-f470-474d-8b7a-671a12c393a6")
    private String messageId;
    @ApiModelProperty(notes = "Message timestamp", example = "2019-08-23T10:46:42.5803489")
    private String messageTimestamp;

    @ApiModelProperty(notes = "City id of weather information", example = "2857943")
    private int id;
    @ApiModelProperty(notes = "City name of weather information", example = "Oelde")
    private String name;
    @ApiModelProperty(notes = "Main weather condition", example = "Clear")
    private String main;
    @ApiModelProperty(notes = "Description of current weather", example = "clear sky")
    private String description;
    @ApiModelProperty(notes = "Url of current weather icon", example = "http://openweathermap.org/img/wn/01d@2x.png")
    private String iconUrl;
    @ApiModelProperty(notes = "Current temperature in celsius", example = "22.95")
    private float temperature;
    @ApiModelProperty(notes = "Current pressure in hPa", example = "1026")
    private float pressure;
    @ApiModelProperty(notes = "Current humidity in percent", example = "40")
    private float humidity;
    @ApiModelProperty(notes = "Current wind speed in meters per second", example = "0.5")
    private float windSpeed;
    @ApiModelProperty(notes = "Current wind direction in meteorological degree", example = "220")
    private int windDegree;

    private CurrentWeatherDto() {
        messageId = UUID.randomUUID().toString();
        messageTimestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    static CurrentWeatherDto toDto(CurrentWeather currentWeather, String iconUrlTemplate) {
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
