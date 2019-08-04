package be.niedel.tonk.adapter.webapi.springsocket;

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

    private final InMemoryMatchRepository matchRepository;
    private final InMemorySessionRepository sessionRepository;

    private final CreateMatchUseCase createMatchUseCase;

    public MatchController(InMemoryMatchRepository matchRepository, InMemorySessionRepository sessionRepository, CreateMatchUseCase createMatchUseCase) {
        this.sessionRepository = sessionRepository;
        this.matchRepository = matchRepository;
        this.createMatchUseCase = createMatchUseCase;
    }

    @MessageMapping("/register-match-invite")
    @SendTo("/topic/match-invites")
    public MatchCreateResponse createMatch(@Payload MatchCreateRequest matchCreateRequest) {
        // TODO: Refactor, this check shouldn't be done here.
        if (!sessionRepository.getAllUsersnames()
                .containsAll(List.of(
                        matchCreateRequest.getPlayer().getUsername(),
                        matchCreateRequest.getOtherPlayer().getUsername()))) {
            throw new IllegalArgumentException("At least one user was not signed in or does not exist...");
        }
        return createMatchUseCase.process(matchCreateRequest);
    }

    @MessageMapping("/match/{matchId}")
    @SendTo("/topic/match/{matchId}")
    public MatchDataDto getMatchData(@DestinationVariable String matchId, @Payload MatchDataDto matchDataDto) {
        return matchDataDto;
    }

}
