package de.mbis.demo.weather.service.provider;

import de.mbis.demo.weather.service.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherProvider {
    @Value("${provider.openweather.url}")
    private String url;
    @Value("${provider.openweather.apikey}")
    private String apiKey;

    public CurrentWeather getCurrentWeatherByCityName(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, CurrentWeather.class, cityName, apiKey);
    }

}
