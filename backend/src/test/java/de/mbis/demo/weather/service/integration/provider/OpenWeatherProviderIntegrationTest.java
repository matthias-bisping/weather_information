package de.mbis.demo.weather.service.integration.provider;

import de.mbis.demo.weather.service.model.CurrentWeather;
import de.mbis.demo.weather.service.provider.OpenWeatherProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherProviderIntegrationTest {

    @Autowired
    private OpenWeatherProvider provider;

    @Test
    public void receive_a_valid_current_weather_result() {
        CurrentWeather result = provider.getCurrentWeatherByCityName("Oelde");
        assertNotNull(result);
    }

}
