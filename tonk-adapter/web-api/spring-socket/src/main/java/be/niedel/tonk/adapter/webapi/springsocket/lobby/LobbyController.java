package be.niedel.tonk.adapter.webapi.springsocket.lobby;

import be.niedel.tonk.application.lobby.sendmessage.LobbyMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LobbyController {

    @MessageMapping("/send-lobby-message")
    @SendTo("/topic/lobby-messages")
    public LobbyMessageDto sendMesssage(@Payload LobbyMessageDto message) {
        return message;
    }

}
