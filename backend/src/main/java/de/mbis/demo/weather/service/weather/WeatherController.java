package de.mbis.demo.weather.service.weather;

import de.mbis.demo.weather.service.exceptions.CityNotFoundException;
import de.mbis.demo.weather.service.provider.OpenWeatherProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("rest/")
public class WeatherController {
    private OpenWeatherProvider provider;

    @Value("${provider.openweather.icon.url.template}")
    private String iconUrlTemplate;

    public WeatherController(OpenWeatherProvider provider) {
        this.provider = provider;
    }

    @RequestMapping("/currentWeather")
    public CurrentWeatherDto currentWeather(@RequestParam("city") String city) {
        try {
            return CurrentWeatherDto.toDto(provider.getCurrentWeatherByCityName(city), iconUrlTemplate);
        } catch (CityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
