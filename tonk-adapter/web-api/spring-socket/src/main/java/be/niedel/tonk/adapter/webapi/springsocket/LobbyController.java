package be.niedel.tonk.adapter.webapi.springsocket;

import be.niedel.tonk.adapter.webapi.springsocket.messages.ChatMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LobbyController {

    private final InMemorySessionRepository repository;

    public LobbyController(InMemorySessionRepository repository) {
        this.repository = repository;
    }

    @MessageMapping("/lobby-chat")
    @SendTo("/topic/lobby-chat-messages")
    public ChatMessageDto sendMesssage(@Payload ChatMessageDto message) {
        return message;
    }

    @MessageMapping("/lobby-online-users")
    @SendTo("/topic/lobby-online-users-results")
    public String[] getOnlineUsers() {
        return repository.getAllUsersnames()
                .toArray(new String[0]);
    }



}
