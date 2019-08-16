package de.mbis.demo.weather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WeatherInformationApplicationTests {

    @Test
    @DisplayName("it should start the spring app with contexts")
    public void contextLoads() {
    }

}
