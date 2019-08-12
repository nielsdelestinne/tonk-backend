package be.niedel.tonk.application.gamesession.remove;

import be.nielde.tonk.domain.gamesession.GameSessionId;
import be.nielde.tonk.domain.gamesession.GameSessionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RemoveGameSessionUseCaseTest {

    @Test
    void process_givenAGameSessionIdForWhichAGameSessionExists_thenReturnTrue() {
        // GIVEN
        GameSessionRepository gameSessionRepoMock = Mockito.mock(GameSessionRepository.class);
        when(gameSessionRepoMock.remove(any(GameSessionId.class))).thenReturn(true);
        RemoveGameSessionUseCase removeGameSessionUseCase = new RemoveGameSessionUseCase(gameSessionRepoMock);

        // WHEN
        boolean isRemoved = removeGameSessionUseCase.process(GameSessionId.create(UUID.randomUUID().toString()));

        // THEN
        Assertions.assertThat(isRemoved).isTrue();
    }

    @Test
    void process_givenAGameSessionIdForWhichNoGameSessionExists_thenReturnFalse() {
        // GIVEN
        GameSessionRepository gameSessionRepoMock = Mockito.mock(GameSessionRepository.class);
        when(gameSessionRepoMock.remove(any(GameSessionId.class))).thenReturn(false);
        RemoveGameSessionUseCase removeGameSessionUseCase = new RemoveGameSessionUseCase(gameSessionRepoMock);

        // WHEN
        boolean isRemoved = removeGameSessionUseCase.process(GameSessionId.create(UUID.randomUUID().toString()));

        // THEN
        Assertions.assertThat(isRemoved).isFalse();
    }
}
