package be.niedel.tonk.adapter.webapi.springwebflux;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static java.time.LocalDateTime.now;

@Controller
public class LobbyController {

    @MessageMapping("/lobby-sign-in")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        return new OutputMessage(message.getFrom(), message.getText(), now().toString());
    }

}
