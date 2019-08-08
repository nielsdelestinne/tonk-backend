package be.niedel.tonk.adapter.webapi.springsocket.gamesession;

import be.niedel.tonk.application.gamesession.create.CreateGameSessionUseCase;
import be.niedel.tonk.application.gamesession.remove.RemoveGameSessionUseCase;
import be.nielde.tonk.domain.gamesession.GameSessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

@Component
public class GameSessionInterceptor implements ChannelInterceptor {

    private static final String MESSAGE_TYPE_FOR_CONNECT = "CONNECT";
    private static final String MESSAGE_TYPE_FOR_DISCONNECT = "DISCONNECT";
    private static final String HEADER_KEY_FOR_MESSAGE_TYPE = "simpMessageType";
    private static final String NATIVE_HEADER_KEY_FOR_USERNAME = "username";

    private Logger logger = LoggerFactory.getLogger(GameSessionInterceptor.class);

    private final CreateGameSessionUseCase createGameSessionUseCase;
    private final RemoveGameSessionUseCase removeGameSessionUseCase;

    public GameSessionInterceptor(CreateGameSessionUseCase createGameSessionUseCase,
                                  RemoveGameSessionUseCase removeGameSessionUseCase) {
        this.createGameSessionUseCase = createGameSessionUseCase;
        this.removeGameSessionUseCase = removeGameSessionUseCase;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        ofNullable(message.getHeaders().get(HEADER_KEY_FOR_MESSAGE_TYPE))
                .ifPresent(messageType -> processMessageType(messageType.toString(), message));
        return message;
    }

    private void processMessageType(String messageType, Message<?> message) {
        var stompHeaderAccessor = StompHeaderAccessor.wrap(message);
        switch (messageType) {
            case MESSAGE_TYPE_FOR_CONNECT:
                storeNewSessionOnConnect(stompHeaderAccessor);
                break;
            case MESSAGE_TYPE_FOR_DISCONNECT:
                removeExistingSessionOnDisconnect(stompHeaderAccessor);
                break;
            default:
                break;
        }
    }

    private void removeExistingSessionOnDisconnect(StompHeaderAccessor stompHeaderAccessor) {
        if (removeGameSessionUseCase.process(GameSessionId.create(stompHeaderAccessor.getSessionId()))) {
            logger.info("Client DISCONNECTED: sessionId " + stompHeaderAccessor.getSessionId());
        } else {
            logger.error("Client with sessionId " + stompHeaderAccessor.getSessionId()
                    + "tried to disconnect but did not have a Game Session!");
        }
    }

    private void storeNewSessionOnConnect(StompHeaderAccessor stompHeaderAccessor) {
        String playerUsername = requireNonNull(stompHeaderAccessor
                .getNativeHeader(NATIVE_HEADER_KEY_FOR_USERNAME)).get(0);
        String sessionId = stompHeaderAccessor.getSessionId();
        createGameSessionUseCase.process(GameSessionId.create(sessionId), playerUsername);
        logger.info("New client CONNECTED: sessionId " + sessionId + ", username " + playerUsername);
    }
}
