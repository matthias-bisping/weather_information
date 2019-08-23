package de.mbis.demo.weather.service.updater;

import de.mbis.demo.weather.service.model.CurrentWeather;
import de.mbis.demo.weather.service.weather.CurrentWeatherDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WeatherUpdaterController {
    @Value("${provider.openweather.icon.url.template}")
    private String iconUrlTemplate;

    private SimpMessagingTemplate template;

    public WeatherUpdaterController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void push(CurrentWeather currentWeather) {
        if (currentWeather != null)
            template.convertAndSend("/topic", CurrentWeatherDto.toDto(currentWeather, iconUrlTemplate));
    }

}
