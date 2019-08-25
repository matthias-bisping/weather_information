package de.mbis.demo.weather.service.controller;

import de.mbis.demo.weather.service.dto.CurrentWeatherDto;
import de.mbis.demo.weather.service.exceptions.CityNotFoundException;
import de.mbis.demo.weather.service.provider.OpenWeatherProvider;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("rest/")
@Api(value = "Current Weather Information")
public class WeatherController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OpenWeatherProvider provider;

    @Value("${provider.openweather.icon.url.template}")
    private String iconUrlTemplate;

    public WeatherController(OpenWeatherProvider provider) {
        this.provider = provider;
    }

    @RequestMapping(value = "/currentWeather", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the current weather for given city name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved weather information"),
            @ApiResponse(code = 400, message = "Given city name not found"),
    })
    public CurrentWeatherDto currentWeather(@ApiParam(value = "Valid city name", required = true) @RequestParam("city") String city) {
        logger.info("try to receive current weather for: {}", city);
        try {
            return CurrentWeatherDto.toDto(provider.getCurrentWeatherByCityName(city), iconUrlTemplate);
        } catch (CityNotFoundException e) {
            logger.error("error on receiving current weather", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
