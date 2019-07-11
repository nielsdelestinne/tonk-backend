package be.niedel.tonk.adapter.webapi.springsocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

@Component
public class SessionChannelInterceptor implements ChannelInterceptor {

    private static final String MESSAGE_TYPE_FOR_CONNECT = "CONNECT";
    private static final String MESSAGE_TYPE_FOR_DISCONNECT = "DISCONNECT";
    private static final String HEADER_KEY_FOR_MESSAGE_TYPE = "simpMessageType";
    private static final String NATIVE_HEADER_KEY_FOR_USERNAME = "username";

    private Logger logger = LoggerFactory.getLogger(SessionChannelInterceptor.class);

    private final InMemorySessionRepository repository;

    public SessionChannelInterceptor(@Autowired InMemorySessionRepository repository) {
        this.repository = repository;
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
            default :
                break;
        }
    }

    private void removeExistingSessionOnDisconnect(StompHeaderAccessor stompHeaderAccessor) {
        repository.remove(stompHeaderAccessor.getSessionId());
        logger.info("Client DISCONNECTED: sessionId "+ stompHeaderAccessor.getSessionId());
    }

    private void storeNewSessionOnConnect(StompHeaderAccessor stompHeaderAccessor) {
        String username = requireNonNull(stompHeaderAccessor.getNativeHeader(NATIVE_HEADER_KEY_FOR_USERNAME)).get(0);
        String sessionId = stompHeaderAccessor.getSessionId();
        repository.add(sessionId, username);
        logger.info("New client CONNECTED: sessionId " + sessionId + ", username " + username);
    }
}
