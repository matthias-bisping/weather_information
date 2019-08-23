package de.mbis.demo.weather.service.updater;

import de.mbis.demo.weather.service.controller.WeatherUpdaterController;
import de.mbis.demo.weather.service.model.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherUpdateRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WeatherUpdaterController controller;

    private CurrentWeather currentWeather;

    public WeatherUpdateRepository(WeatherUpdaterController controller) {
        this.controller = controller;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    void setCurrentWeather(CurrentWeather currentWeather) {
        logger.info("Repository: Update current weather");
        this.currentWeather = currentWeather;
        controller.push(currentWeather);
    }
}
