package de.mbis.demo.weather.service.integration.provider;

import de.mbis.demo.weather.service.exceptions.CityNotFoundException;
import de.mbis.demo.weather.service.model.CurrentWeather;
import de.mbis.demo.weather.service.unit.provider.OpenWeatherProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OpenWeatherProviderIntegrationTest {

    @Autowired
    private OpenWeatherProvider provider;

    @Tag("manual")
    @Test
    @DisplayName("it should receive a valid current weather result")
    public void receive_a_valid_current_weather_result() throws CityNotFoundException {
        CurrentWeather result = provider.getCurrentWeatherByCityName("Oelde");
        assertNotNull(result);
    }

}
