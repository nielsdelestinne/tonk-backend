package be.niedel.tonk.adapter.webapi.restful.playerregistration;

import be.niedel.tonk.application.playerregistration.create.CreatePlayerRegistrationRequest;
import be.niedel.tonk.application.playerregistration.create.CreatePlayerRegistrationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static be.niedel.tonk.adapter.webapi.restful.RestfulWebApiProperties.BASE_REST_URL;

@RestController
@RequestMapping(PlayerRegistrationController.PLAYER_REGISTRATION_PATH)
public class PlayerRegistrationController {

    static final String PLAYER_REGISTRATION_PATH = BASE_REST_URL + "/playerregistration";

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreatePlayerRegistrationResponse createPlayerRegistration(@RequestBody CreatePlayerRegistrationRequest createPlayerRegistrationRequest) {
        return new CreatePlayerRegistrationResponse();
    }


}
