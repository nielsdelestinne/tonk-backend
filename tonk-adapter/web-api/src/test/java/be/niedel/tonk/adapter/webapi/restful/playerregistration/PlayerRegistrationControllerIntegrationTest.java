package be.niedel.tonk.adapter.webapi.restful.playerregistration;

import be.niedel.tonk.application.playerregistration.create.CreatePlayerRegistrationRequest;
import be.niedel.tonk.application.playerregistration.create.CreatePlayerRegistrationResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static be.niedel.tonk.adapter.webapi.restful.playerregistration.PlayerRegistrationController.PLAYER_REGISTRATION_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class PlayerRegistrationControllerIntegrationTest {

    @Value("${server.port}")
    private int port;

    @Test
    void createPlayerRegistration_givenAValidPlayerRegistrationRequest_thenPlayerRegistrationIsCreatedAndAValidPlayerRegistrationResponseIsReturned() {
        CreatePlayerRegistrationResponse actualCreatePlayerRegistrationResponse =
                given()
                    .body(new CreatePlayerRegistrationRequest())
                    .accept(JSON)
                    .contentType(JSON)
                .when()
                    .port(port)
                    .post(PLAYER_REGISTRATION_PATH)
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.CREATED.value())
                    .extract().as(CreatePlayerRegistrationResponse.class);

        Assertions.assertThat(actualCreatePlayerRegistrationResponse)
                .isEqualToComparingFieldByField(new CreatePlayerRegistrationResponse());
    }


}
