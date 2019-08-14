package de.mbis.demo.weather.service.unit.provider;

import de.mbis.demo.weather.service.exceptions.CityNotFoundException;
import de.mbis.demo.weather.service.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherProvider {
    @Value("${provider.openweather.url}")
    private String url;
    @Value("${provider.openweather.apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public OpenWeatherProvider(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public CurrentWeather getCurrentWeatherByCityName(String cityName) throws CityNotFoundException {
        try {
            return restTemplate.getForObject(url, CurrentWeather.class, cityName, apiKey);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException(String.format("city \"%s\" not found", cityName), e);
        }

    }
}
