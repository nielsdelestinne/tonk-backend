package be.niedel.tonk.adapter.webapi.springsocket;

import be.niedel.tonk.adapter.webapi.springsocket.messages.MatchInvite;
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
    public MatchInvite registerMatchInvite(@Payload MatchInvite matchInvite) {
        if (!sessionRepository.getAllUsersnames()
                .containsAll(List.of(matchInvite.getPlayer(), matchInvite.getOtherPlayer()))) {
            throw new IllegalArgumentException("At least one user was not signed in or does not exist...");
        }
        String matchId = UUID.randomUUID().toString();
        matchRepository.add(matchId,
                new InMemoryMatchRepository.MatchData()
                        .setPlayer(matchInvite.getPlayer())
                        .setOtherPlayer(matchInvite.getOtherPlayer()));

        return matchInvite.setMatchId(matchId);
    }

}
