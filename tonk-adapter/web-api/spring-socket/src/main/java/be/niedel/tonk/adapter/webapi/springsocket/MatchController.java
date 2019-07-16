package be.niedel.tonk.adapter.webapi.springsocket;

import be.niedel.tonk.adapter.webapi.springsocket.messages.MatchDataDto;
import be.niedel.tonk.adapter.webapi.springsocket.messages.MatchInviteDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class MatchController {

    private final InMemoryMatchRepository matchRepository;
    private final InMemorySessionRepository sessionRepository;

    public MatchController(InMemoryMatchRepository matchRepository, InMemorySessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        this.matchRepository = matchRepository;
    }

    @MessageMapping("/register-match-invite")
    @SendTo("/topic/match-invites")
    public MatchInviteDto registerMatchInvite(@Payload MatchInviteDto matchInviteDto) {
        if (!sessionRepository.getAllUsersnames()
                .containsAll(List.of(matchInviteDto.getPlayer(), matchInviteDto.getOtherPlayer()))) {
            throw new IllegalArgumentException("At least one user was not signed in or does not exist...");
        }
        String matchId = UUID.randomUUID().toString();
        matchRepository.add(matchId,
                new InMemoryMatchRepository.MatchInvite()
                        .setPlayer(matchInviteDto.getPlayer())
                        .setOtherPlayer(matchInviteDto.getOtherPlayer()));

        return matchInviteDto.setMatchId(matchId);
    }

    @MessageMapping("/match/{matchId}")
    @SendTo("/topic/match/{matchId}")
    public MatchDataDto getMatchData(@DestinationVariable String matchId, @Payload MatchDataDto matchDataDto) {
        return matchDataDto;
    }

}
