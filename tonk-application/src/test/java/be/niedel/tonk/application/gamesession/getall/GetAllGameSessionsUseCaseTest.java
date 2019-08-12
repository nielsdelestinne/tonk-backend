package be.niedel.tonk.application.gamesession.getall;

import be.niedel.tonk.domain.gamesession.GameSession;
import be.niedel.tonk.domain.gamesession.GameSessionId;
import be.niedel.tonk.domain.gamesession.GameSessionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

class GetAllGameSessionsUseCaseTest {

    @Test
    void process_givenMultipleGameSessionsInTheRepository_thenReturnAllThoseGameSessions() {
        // GIVEN
        GameSessionRepository gameSessionRepoMock = Mockito.mock(GameSessionRepository.class);
        List<GameSession> expectedGameSessions = createThreeValidGameSessions();
        when(gameSessionRepoMock.getAll()).thenReturn(expectedGameSessions);
        GetAllGameSessionsUseCase getAllGameSessionsUseCase = new GetAllGameSessionsUseCase(gameSessionRepoMock);

        // WHEN
        Collection<GameSession> actualGameSessions = getAllGameSessionsUseCase.process();

        // THEN
        Assertions.assertThat(actualGameSessions)
                .containsExactlyInAnyOrder(expectedGameSessions.toArray(GameSession[]::new));
    }

    private List<GameSession> createThreeValidGameSessions() {
        return List.of(
                new GameSession(GameSessionId.create(UUID.randomUUID().toString()), "Jim"),
                new GameSession(GameSessionId.create(UUID.randomUUID().toString()), "Boris"),
                new GameSession(GameSessionId.create(UUID.randomUUID().toString()), "Jane")
        );
    }
}
