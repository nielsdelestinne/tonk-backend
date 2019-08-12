package be.niedel.tonk.application.lobby.sendmessage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SendMessageLobbyUseCaseTest {

    @Test
    void process_givenAMessageToSend_thenSimplyReturnThatMessage() {
        // GIVEN
        LobbyMessageDto messageToSend = new LobbyMessageDto("JimTheSender", "Hello!");

        // WHEN
        SendMessageLobbyUseCase sendMessageLobbyUseCase = new SendMessageLobbyUseCase();
        LobbyMessageDto returnedMessage = sendMessageLobbyUseCase.process(messageToSend);

        // THEN
        assertThat(returnedMessage)
                .isEqualToComparingFieldByField(messageToSend);
    }

}
