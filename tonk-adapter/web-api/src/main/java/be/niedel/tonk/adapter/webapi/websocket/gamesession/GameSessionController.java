package be.niedel.tonk.adapter.webapi.websocket.gamesession;

import be.niedel.tonk.application.gamesession.getall.GetAllGameSessionsUseCase;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameSessionController {

    private final GetAllGameSessionsUseCase getAllGameSessionsUseCase;

    public GameSessionController(GetAllGameSessionsUseCase getAllGameSessionsUseCase) {
        this.getAllGameSessionsUseCase = getAllGameSessionsUseCase;
    }

    @MessageMapping("/my-game-session-is-active")
    @SendTo("/topic/a-new-game-session-is-active")
    public String[] getAllGameSessions() {
        return getAllGameSessionsUseCase
                .process()
                .stream()
                    .map(gameSession -> gameSession.getPlayer().getUsername())
                    .toArray(String[]::new);
    }


}
