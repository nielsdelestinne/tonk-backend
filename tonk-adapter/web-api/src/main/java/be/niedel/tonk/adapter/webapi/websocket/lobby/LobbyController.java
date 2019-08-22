package be.niedel.tonk.adapter.webapi.websocket.lobby;

import be.niedel.tonk.application.lobby.sendmessage.LobbyMessageDto;
import be.niedel.tonk.application.lobby.sendmessage.SendMessageLobbyUseCase;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LobbyController {

    private final SendMessageLobbyUseCase sendMessageLobbyUseCase;

    public LobbyController(SendMessageLobbyUseCase sendMessageLobbyUseCase) {
        this.sendMessageLobbyUseCase = sendMessageLobbyUseCase;
    }

    @MessageMapping("/send-lobby-message")
    @SendTo("/topic/lobby-messages")
    public LobbyMessageDto sendMesssage(@Payload LobbyMessageDto message) {
        return sendMessageLobbyUseCase.process(message);
    }

}
