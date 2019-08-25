package de.mbis.demo.weather.service.controller;

import de.mbis.demo.weather.service.model.Properties;
import de.mbis.demo.weather.service.updater.WeatherUpdaterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Update city and interval properties")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated properties"),
            @ApiResponse(code = 500, message = "Error updating properties"),
    })
    public Properties updateCityAndInterval(
            @RequestBody Properties properties
    ) {
        logger.info("try to update properties to: {}, {}", properties.getCity(), properties.getInterval());
        try {
            weatherUpdaterService.update(properties.getCity(), properties.getInterval());
            return properties;
        } catch (SchedulerException e) {
            logger.error("error on updating properties", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
