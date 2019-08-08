package be.niedel.tonk.adapter.webapi.springsocket.match;

import be.niedel.tonk.adapter.webapi.springsocket.messages.MatchDataDto;
import be.niedel.tonk.application.match.create.CreateMatchUseCase;
import be.niedel.tonk.application.match.create.MatchCreateRequest;
import be.niedel.tonk.application.match.create.MatchCreateResponse;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MatchController {

    private final CreateMatchUseCase createMatchUseCase;

    public MatchController(CreateMatchUseCase createMatchUseCase) {
        this.createMatchUseCase = createMatchUseCase;
    }

    @MessageMapping("/register-match-invite")
    @SendTo("/topic/match-invites")
    public MatchCreateResponse createMatch(@Payload MatchCreateRequest matchCreateRequest) {
        return createMatchUseCase.process(matchCreateRequest);
    }


    // TODO: Create useCase just as in LobbyController for messages
    @MessageMapping("/match/{matchId}")
    @SendTo("/topic/match/{matchId}")
    public MatchDataDto getMatchData(@DestinationVariable String matchId, @Payload MatchDataDto matchDataDto) {
        return matchDataDto;
    }

}