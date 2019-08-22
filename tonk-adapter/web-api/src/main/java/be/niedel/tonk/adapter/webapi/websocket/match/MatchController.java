package be.niedel.tonk.adapter.webapi.websocket.match;

import be.niedel.tonk.application.match.create.CreateMatchUseCase;
import be.niedel.tonk.application.match.create.MatchCreateRequest;
import be.niedel.tonk.application.match.create.MatchCreateResponse;
import be.niedel.tonk.application.match.sendmatchactivity.MatchActivityDto;
import be.niedel.tonk.application.match.sendmatchactivity.SendMatchActivityUseCase;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MatchController {

    private final CreateMatchUseCase createMatchUseCase;
    private final SendMatchActivityUseCase sendMatchActivityUseCase;

    public MatchController(CreateMatchUseCase createMatchUseCase,
                           SendMatchActivityUseCase sendMatchActivityUseCase) {
        this.createMatchUseCase = createMatchUseCase;
        this.sendMatchActivityUseCase = sendMatchActivityUseCase;
    }

    @MessageMapping("/register-match-invite")
    @SendTo("/topic/match-invites")
    public MatchCreateResponse createMatch(@Payload MatchCreateRequest matchCreateRequest) {
        return createMatchUseCase.process(matchCreateRequest);
    }


    @MessageMapping("/match/{matchId}")
    @SendTo("/topic/match/{matchId}")
    public MatchActivityDto matchActivity(@DestinationVariable String matchId, @Payload MatchActivityDto matchActivityDto) {
        return sendMatchActivityUseCase.process(matchActivityDto);
    }

}
