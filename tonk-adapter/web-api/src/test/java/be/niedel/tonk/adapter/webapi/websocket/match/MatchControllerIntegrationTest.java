package be.niedel.tonk.adapter.webapi.websocket.match;

import be.niedel.tonk.application.match.create.MatchCreateRequest;
import be.niedel.tonk.application.match.create.MatchCreateResponse;
import be.niedel.tonk.application.match.create.PlayerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class MatchControllerIntegrationTest {

    @Value("${server.port}")
    private int port;

    private final CompletableFuture<MatchCreateResponse> completableFuture;

    MatchControllerIntegrationTest() {
        completableFuture = new CompletableFuture<>();
    }

    private String getBaseUrl() {
        return "ws://localhost:" + port + "/socket";
    }

    @Test
    void createMatch_givenTwoValidConnectionsForTwoPlayers_thenMatchInviteIsCreated() throws InterruptedException, ExecutionException, TimeoutException {
        // GIVEN
        createStompClientConnectionFor("Ann");
        StompSession stompSession = createStompClientConnectionFor("Jim");


        // WHEN
        stompSession.subscribe("/topic/match-invites", new CreateGameStompFrameHandler());
        stompSession.send("/app/register-match-invite",
                new MatchCreateRequest(
                        new PlayerDto(UUID.randomUUID().toString(), "Jim"),
                        new PlayerDto(UUID.randomUUID().toString(), "Ann")
                ));
        MatchCreateResponse matchCreateResponse = completableFuture.get(5, SECONDS);

        // THEN
        Assertions.assertThat(matchCreateResponse).isNotNull();
        Assertions.assertThat(matchCreateResponse.getMatchId()).isNotBlank();
        Assertions.assertThat(matchCreateResponse.getPlayer().getUsername()).isEqualTo("Jim");
        Assertions.assertThat(matchCreateResponse.getOtherPlayer().getUsername()).isEqualTo("Ann");
    }

    private StompSession createStompClientConnectionFor(String username) throws InterruptedException, ExecutionException, TimeoutException {
        return createStomClient().connect(
                getBaseUrl(), new WebSocketHttpHeaders(), createUsernameHeader(username), new StompSessionHandlerAdapter() {
                })
                .get(1, SECONDS);
    }

    private StompHeaders createUsernameHeader(String username) {
        StompHeaders connectHeaders = new StompHeaders();
        connectHeaders.add("username", username);
        return connectHeaders;
    }

    private WebSocketStompClient createStomClient() {
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient;
    }

    private class CreateGameStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return MatchCreateResponse.class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            completableFuture.complete((MatchCreateResponse) o);
        }
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

}
