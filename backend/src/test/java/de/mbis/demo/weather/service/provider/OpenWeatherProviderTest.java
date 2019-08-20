package de.mbis.demo.weather.service.provider;

import de.mbis.demo.weather.service.exceptions.CityNotFoundException;
import de.mbis.demo.weather.service.model.CurrentWeather;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(OpenWeatherProvider.class)
public class OpenWeatherProviderTest {

    private static final String SUCCESS = "{\"coord\":{\"lon\":8.15,\"lat\":51.83},\"weather\":[{\"id\":800,\"main\":\"Clear\"," +
            "\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":288.15,\"pressure\":1019," +
            "\"humidity\":72,\"temp_min\":288.15,\"temp_max\":288.15},\"visibility\":10000,\"wind\":{\"speed\":3.1,\"deg\":220}," +
            "\"clouds\":{\"all\":0},\"dt\":1565771550,\"sys\":{\"type\":1,\"id\":1304,\"message\":0.0066,\"country\":\"DE\",\"sunrise" +
            "\":1565755787,\"sunset\":1565808885},\"timezone\":7200,\"id\":2857943,\"name\":\"Oelde\",\"cod\":200}";

    @Autowired
    private OpenWeatherProvider provider;

    @Autowired
    private MockRestServiceServer server;

    @Test
    @DisplayName("it should deserialize weather information from provider")
    public void deserialize_weather_information_from_provider() throws CityNotFoundException {
        server.expect(
                requestTo("http://api.openweathermap.org/data/2.5/weather?q=Oelde&APPID=c94345fc6cf1a078f4047180bfb7e34a&units=metric"))
                .andRespond(withSuccess(SUCCESS, MediaType.APPLICATION_JSON));

        CurrentWeather actual = provider.getCurrentWeatherByCityName("Oelde");

        assertEquals(2857943, actual.getId());
        assertEquals("Oelde", actual.getName());

        assertEquals(1, actual.getWeathers().size());
        assertEquals(800, actual.getWeathers().get(0).getId());
        assertEquals("Clear", actual.getWeathers().get(0).getMain());
        assertEquals("clear sky", actual.getWeathers().get(0).getDescription());
        assertEquals("01d", actual.getWeathers().get(0).getIcon());

        assertEquals(288.15, actual.getMain().getTemp(), 0.001);
        assertEquals(1019.0, actual.getMain().getPressure(), 0.001);
        assertEquals(72.0, actual.getMain().getHumidity(), 0.001);

        assertEquals(3.1, actual.getWind().getSpeed(), 0.001);
        assertEquals(220, actual.getWind().getDeg());
    }

    @Test
    @DisplayName("it should throw an exception on wrong city name")
    public void throws_exception_on_city_name_not_found() throws CityNotFoundException {
        server.expect(
                requestTo("http://api.openweathermap.org/data/2.5/weather?q=NotFound&APPID=c94345fc6cf1a078f4047180bfb7e34a&units=metric"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThrows(CityNotFoundException.class, () -> provider.getCurrentWeatherByCityName("NotFound"));
    }

}
