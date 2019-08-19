package de.mbis.demo.weather.service.serverstatus;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ServerStatusControllerTest {

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    public void init() {
        RestAssured.baseURI = String.format("http://localhost:%d/weather", serverPort);
    }

    @Test
    @DisplayName("it should return a valid server status")
    public void correct_server_status() {
        when()
                .get("/status")
                .then()
                .body("status", equalTo("OK"))
                .body("session", is(notNullValue()))
                .statusCode(200);
    }
}
