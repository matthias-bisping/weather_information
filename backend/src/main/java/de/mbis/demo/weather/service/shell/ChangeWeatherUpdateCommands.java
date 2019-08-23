package de.mbis.demo.weather.service.shell;

import de.mbis.demo.weather.service.model.CurrentWeather;
import de.mbis.demo.weather.service.updater.WeatherUpdateRepository;
import de.mbis.demo.weather.service.updater.WeatherUpdaterService;
import org.quartz.SchedulerException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ChangeWeatherUpdateCommands {
    private WeatherUpdaterService weatherUpdaterService;
    private WeatherUpdateRepository repository;

    public ChangeWeatherUpdateCommands(WeatherUpdaterService weatherUpdaterService, WeatherUpdateRepository repository) {
        this.weatherUpdaterService = weatherUpdaterService;
        this.repository = repository;
    }

    @ShellMethod("Show current weather")
    public String show() {
        CurrentWeather currentWeather = repository.getCurrentWeather();
        if(currentWeather == null)
            return "No weather information available";

        return String.format("Current updated city: %s", currentWeather.getName());
    }

    @ShellMethod("Change weather update configuration")
    public String update(
            @ShellOption(defaultValue = "Berlin") String city,
            @ShellOption(defaultValue = "60") int interval
    ) throws SchedulerException {
        weatherUpdaterService.update(city, interval);
        return String.format("Config changed to: %s, %d sec", city, interval);
    }
}