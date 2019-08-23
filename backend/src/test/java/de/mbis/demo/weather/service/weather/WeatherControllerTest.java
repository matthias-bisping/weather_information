package de.mbis.demo.weather.service.weather;

import de.mbis.demo.weather.service.exceptions.CityNotFoundException;
import de.mbis.demo.weather.service.provider.OpenWeatherProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public class WeatherControllerTest {
    private MockMvc mockMvc;

    @Mock
    private OpenWeatherProvider provider;

    @InjectMocks
    private WeatherController controller;

    @BeforeEach
    void init() throws CityNotFoundException {
        Mockito.when(provider.getCurrentWeatherByCityName("Oelde")).thenReturn(CurrentWeatherBuilder.create().build());
        Mockito.when(provider.getCurrentWeatherByCityName("wrong")).thenThrow(new CityNotFoundException("City not found"));

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @DisplayName("it should receive a valid current weather by city")
    @Test
    void get_valid_current_weather_by_city() throws Exception {
        MockHttpServletResponse actual = mockMvc.perform(get("/rest/currentWeather?city=Oelde")).andReturn().getResponse();

        assertThat(actual.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(actual.getContentAsString(), containsString("\"id\":2857943"));
        assertThat(actual.getContentAsString(), containsString("\"name\":\"Oelde\""));
        assertThat(actual.getContentAsString(), containsString("\"description\":\"clear sky\""));
    }

    @DisplayName("it should receive an error on a unknown city")
    @Test
    void get_error_on_unknown_city() throws Exception {
        MockHttpServletResponse actual = mockMvc.perform(get("/rest/currentWeather?city=wrong")).andReturn().getResponse();

        assertThat(actual.getStatus(), is(equalTo(HttpStatus.BAD_REQUEST.value())));
        assertThat(actual.getErrorMessage(), is(equalTo("City not found")));
    }
}
