package de.mbis.demo.weather.service.controller;

import de.mbis.demo.weather.service.updater.WeatherUpdaterService;
import io.swagger.annotations.*;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("rest/properties")
@Api(value = "Properties Updater")
public class PropertiesUpdaterController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WeatherUpdaterService weatherUpdaterService;

    public PropertiesUpdaterController(WeatherUpdaterService weatherUpdaterService) {
        this.weatherUpdaterService = weatherUpdaterService;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ApiOperation(value = "Update city and interval properties")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated properties"),
            @ApiResponse(code = 500, message = "Error updating properties"),
    })
    public void updateCityAndInterval(
            @ApiParam(value = "Valid city name", required = true) @RequestParam("city") String city,
            @ApiParam(value = "Valid interval", required = true) @RequestParam("interval") int interval
    ) {
        logger.info("try to update properties to: {}, {}", city, interval);
        try {
            weatherUpdaterService.update(city, interval);
        } catch (SchedulerException e) {
            logger.error("error on updating properties", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
