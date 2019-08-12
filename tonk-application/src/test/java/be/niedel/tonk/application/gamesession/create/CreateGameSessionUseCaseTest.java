package be.niedel.tonk.application.gamesession.create;

import be.nielde.tonk.domain.gamesession.GameSession;
import be.nielde.tonk.domain.gamesession.GameSessionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static be.nielde.tonk.domain.gamesession.GameSessionId.create;
import static org.mockito.ArgumentMatchers.any;

class CreateGameSessionUseCaseTest {

    @Test
    void process_givenAGameSessionIdAndPlayerUsername_thenCreateAGameSession() {
        // GIVEN
        var gameSessionRepoMock = Mockito.mock(GameSessionRepository.class);
        CreateGameSessionUseCase createGameSessionUseCase = new CreateGameSessionUseCase(gameSessionRepoMock);

        // WHEN
        createGameSessionUseCase.process(create(UUID.randomUUID().toString()), "Neil");

        // THEN
        Mockito.verify(gameSessionRepoMock)
                .save(any(GameSession.class));
    }
}
