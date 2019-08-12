package be.niedel.tonk.adapter.webapi.springsocket.lobby;

import be.niedel.tonk.application.lobby.sendmessage.SendMessageLobbyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LobbyUseCasesBeanConfiguration {

    @Bean
    public SendMessageLobbyUseCase sendMessageLobbyUseCase() {
        return new SendMessageLobbyUseCase();
    }

}
