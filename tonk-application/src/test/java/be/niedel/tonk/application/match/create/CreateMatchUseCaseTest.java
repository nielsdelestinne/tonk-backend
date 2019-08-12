package be.niedel.tonk.application.match.create;

import be.niedel.tonk.domain.gamesession.*;
import be.niedel.tonk.domain.match.Match;
import be.niedel.tonk.domain.match.MatchRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateMatchUseCaseTest {

    private static final String VALID_PLAYER_USERNAME = "Jim";
    private static final String VALID_OTHER_PLAYER_USERNAME = "Ann";
    private static final String VALID_PLAYER_ID = UUID.randomUUID().toString();
    private static final String VALID_OTHER_PLAYER_ID = UUID.randomUUID().toString();

    @Test
    void process_givenAValidGameSessionForBothPlayers_thenTheMatchIsCreated() {

        // GIVEN
        var expectedMatch = createMatchWithTwoValidPlayers();
        var gameSessionRepositoryMock = gameSessionRepositoryMockGetAllContainsGameSessionsForEachValidPlayer();
        var matchRepositoryMock = matchRepositoryMockSaveReturnsMatch(expectedMatch);

        var createMatchUseCase = new CreateMatchUseCase(matchRepositoryMock, gameSessionRepositoryMock);

        // WHEN
        PlayerDto player = new PlayerDto(VALID_PLAYER_ID, VALID_PLAYER_USERNAME);
        PlayerDto otherPlayer = new PlayerDto(VALID_OTHER_PLAYER_ID, VALID_OTHER_PLAYER_USERNAME);
        MatchCreateResponse actualMatchCreateResponse = createMatchUseCase
                .process(new MatchCreateRequest(player, otherPlayer));

        // THEN
        assertThat(actualMatchCreateResponse)
                .isEqualToComparingFieldByField(MatchCreateResponse.create(expectedMatch.getId().getUuid(), player, otherPlayer));
    }

    @Test
    void process_givenNoValidGameSessionForTheMatchCreateRequest_thenThrowExceptionInsteadOfCreatingTheMatch() {

        // GIVEN
        var createMatchUseCase = new CreateMatchUseCase(
                Mockito.mock(MatchRepository.class),
                gameSessionRepositoryMockGetAllContainsNoGameSessionsForEveryValidPlayer());

        // WHEN & THEN
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> createMatchUseCase
                .process(new MatchCreateRequest(
                        new PlayerDto(VALID_PLAYER_ID, VALID_PLAYER_USERNAME),
                        new PlayerDto(VALID_OTHER_PLAYER_ID, VALID_OTHER_PLAYER_USERNAME))))
                .withMessageContaining("did not have an existing Game Session");
    }

    private MatchRepository matchRepositoryMockSaveReturnsMatch(Match matchToReturn) {
        MatchRepository matchRepositoryMock = Mockito.mock(MatchRepository.class);
        when(matchRepositoryMock.save(any(Match.class))).thenReturn(matchToReturn);
        return matchRepositoryMock;
    }

    private Match createMatchWithTwoValidPlayers() {
        return Match.create(Player.create(PlayerId.create(VALID_PLAYER_ID), VALID_PLAYER_USERNAME),
                Player.create(PlayerId.create(VALID_OTHER_PLAYER_ID), VALID_OTHER_PLAYER_USERNAME));
    }

    private GameSessionRepository gameSessionRepositoryMockGetAllContainsNoGameSessionsForEveryValidPlayer() {
        GameSessionRepository gameSessionRepositoryMock = Mockito.mock(GameSessionRepository.class);
        when(gameSessionRepositoryMock.getAll())
                .thenReturn(List.of(new GameSession(GameSessionId.create(VALID_PLAYER_ID), VALID_PLAYER_USERNAME),
                        new GameSession(GameSessionId.create(UUID.randomUUID().toString()), "Kim"),
                        new GameSession(GameSessionId.create(UUID.randomUUID().toString()), "Bart")));
        return gameSessionRepositoryMock;
    }

    private GameSessionRepository gameSessionRepositoryMockGetAllContainsGameSessionsForEachValidPlayer() {
        GameSessionRepository gameSessionRepositoryMock = Mockito.mock(GameSessionRepository.class);
        when(gameSessionRepositoryMock.getAll())
                .thenReturn(List.of(new GameSession(GameSessionId.create(VALID_PLAYER_ID), VALID_PLAYER_USERNAME),
                        new GameSession(GameSessionId.create(UUID.randomUUID().toString()), "Bella"),
                        new GameSession(GameSessionId.create(VALID_OTHER_PLAYER_ID), VALID_OTHER_PLAYER_USERNAME)));
        return gameSessionRepositoryMock;
    }
}
