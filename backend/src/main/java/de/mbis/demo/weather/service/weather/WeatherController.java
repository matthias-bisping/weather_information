package de.mbis.demo.weather.service.weather;

import de.mbis.demo.weather.service.exceptions.CityNotFoundException;
import de.mbis.demo.weather.service.provider.OpenWeatherProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("rest/")
public class WeatherController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OpenWeatherProvider provider;

    @Value("${provider.openweather.icon.url.template}")
    private String iconUrlTemplate;

    public WeatherController(OpenWeatherProvider provider) {
        this.provider = provider;
    }

    @RequestMapping("/currentWeather")
    public CurrentWeatherDto currentWeather(@RequestParam("city") String city) {
        logger.info("try to receive current weather for: {}", city);
        try {
            return CurrentWeatherDto.toDto(provider.getCurrentWeatherByCityName(city), iconUrlTemplate);
        } catch (CityNotFoundException e) {
            logger.error("error on receiving current weather", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
