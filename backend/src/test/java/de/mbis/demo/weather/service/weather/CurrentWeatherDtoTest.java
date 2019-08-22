package de.mbis.demo.weather.service.weather;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class CurrentWeatherDtoTest {
    private CurrentWeatherBuilder defaultCurrentWeather = CurrentWeatherBuilder
            .create()
            .withId(2857943)
            .withName("Oelde")
            .withWeatherId(800)
            .withMain("Clear")
            .withDescription("clear sky")
            .withIcon("01d")
            .withTemp(288.15f)
            .withPressure(1019.0f)
            .withHumidity(72.0f)
            .withSpeed(3.1f)
            .withDeg(220);

    @DisplayName("it should transform a complete model to a dto")
    @Test
    public void transform_a_complete_model_to_dto() {
        CurrentWeatherDto actual = CurrentWeatherDto.toDto(defaultCurrentWeather.build(), "http://test.de/%s");

        assertThat(actual.getMessageId(), is(notNullValue()));
        assertThat(actual.getMessageTimestamp(), is(notNullValue()));

        assertThat(actual.getId(), is(equalTo(2857943)));
        assertThat(actual.getName(), is(equalTo("Oelde")));
        assertThat(actual.getMain(), is(equalTo("Clear")));
        assertThat(actual.getDescription(), is(equalTo("clear sky")));
        assertThat(actual.getIconUrl(), is(equalTo("http://test.de/01d")));
        assertThat(actual.getTemperature(), is(equalTo(288.15f)));
        assertThat(actual.getPressure(), is(equalTo(1019.0f)));
        assertThat(actual.getHumidity(), is(equalTo(72.0f)));
        assertThat(actual.getWindSpeed(), is(equalTo(3.1f)));
        assertThat(actual.getWindDegree(), is(equalTo(220)));
    }

    @DisplayName("it should transform a model without wind to a dto")
    @Test
    public void transform_model_to_dto_without_wind() {
        CurrentWeatherDto actual = CurrentWeatherDto.toDto(
                defaultCurrentWeather
                        .but()
                        .withoutWind()
                        .build(),
                "http://test.de/%s");

        assertThat(actual.getId(), is(equalTo(2857943)));

        assertThat(actual.getWindSpeed(), is(equalTo(0f)));
        assertThat(actual.getWindDegree(), is(equalTo(0)));
    }

    @DisplayName("it should transform a model without main information to a dto")
    @Test
    public void transform_model_to_dto_without_main() {
        CurrentWeatherDto actual = CurrentWeatherDto.toDto(
                defaultCurrentWeather
                        .but()
                        .withoutMain()
                        .build(),
                "http://test.de/%s");

        assertThat(actual.getId(), is(equalTo(2857943)));

        assertThat(actual.getTemperature(), is(equalTo(0f)));
        assertThat(actual.getPressure(), is(equalTo(0f)));
        assertThat(actual.getHumidity(), is(equalTo(0f)));
    }

    @DisplayName("it should transform a model without weather to a dto")
    @Test
    public void transform_model_to_dto_without_weather() {
        CurrentWeatherDto actual = CurrentWeatherDto.toDto(
                defaultCurrentWeather
                        .but()
                        .withoutWeather()
                        .build(),
                "http://test.de/%s");

        assertThat(actual.getId(), is(equalTo(2857943)));

        assertThat(actual.getMain(), is(equalTo(null)));
        assertThat(actual.getDescription(), is(equalTo(null)));
        assertThat(actual.getIconUrl(), is(equalTo(null)));
    }
}
