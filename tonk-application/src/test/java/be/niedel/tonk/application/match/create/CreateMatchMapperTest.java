package be.niedel.tonk.application.match.create;

import be.niedel.tonk.domain.gamesession.Player;
import be.niedel.tonk.domain.gamesession.PlayerId;
import be.niedel.tonk.domain.match.Match;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CreateMatchMapperTest {

    private static final String PLAYER_ID = UUID.randomUUID().toString();
    private static final String OTHER_PLAYER_ID = UUID.randomUUID().toString();
    private static final String PLAYER_USERNAME = "Kim";
    private static final String OTHER_PLAYER_USERNAME = "Betty";

    @Test
    void toDomain_givenAMatchCreateRequest_thenMapItToAValidMatchObject() {
        // GIVEN
        CreateMatchMapper createMatchMapper = new CreateMatchMapper();

        // WHEN
        Match match = createMatchMapper.toDomain(new MatchCreateRequest(
                new PlayerDto(PLAYER_ID, PLAYER_USERNAME),
                new PlayerDto(OTHER_PLAYER_ID, OTHER_PLAYER_USERNAME)
        ));

        // THEN
        assertThat(match)
                .isEqualToIgnoringGivenFields(Match.create(
                        Player.create(PlayerId.create(PLAYER_ID), PLAYER_USERNAME),
                        Player.create(PlayerId.create(OTHER_PLAYER_ID), OTHER_PLAYER_USERNAME))
                        , "id");
        assertThat(match.getId()).isNotNull();
    }

    @Test
    void toResponse_givenAMatch_thenMapToMatchCreateResponse() {
        // GIVEN
        CreateMatchMapper createMatchMapper = new CreateMatchMapper();
        Match match = Match.create(
                Player.create(PlayerId.create(PLAYER_ID), PLAYER_USERNAME),
                Player.create(PlayerId.create(OTHER_PLAYER_ID), OTHER_PLAYER_USERNAME));

        // WHEN
        MatchCreateResponse matchCreateResponse = createMatchMapper.toResponse(match);

        // THEN
        assertThat(matchCreateResponse)
                .isEqualToComparingFieldByField(
                        MatchCreateResponse.create(
                                match.getId().getUuid(),
                                new PlayerDto(PLAYER_ID, PLAYER_USERNAME),
                                new PlayerDto(OTHER_PLAYER_ID, OTHER_PLAYER_USERNAME)));
    }
}
