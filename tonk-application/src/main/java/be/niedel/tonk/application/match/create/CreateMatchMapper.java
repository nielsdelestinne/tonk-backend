package be.niedel.tonk.application.match.create;

import be.niedel.tonk.application.Mapper;
import be.niedel.tonk.domain.match.Match;
import be.nielde.tonk.domain.gamesession.Player;
import be.nielde.tonk.domain.gamesession.PlayerId;

public class CreateMatchMapper implements Mapper<MatchCreateRequest, MatchCreateResponse, Match> {

    @Override
    public Match toDomain(MatchCreateRequest matchCreateRequest) {
        return Match.create(
                Player.create(
                        PlayerId.create(matchCreateRequest.getPlayer().getId()),
                        matchCreateRequest.getPlayer().getUsername()),
                Player.create(
                        PlayerId.create(matchCreateRequest.getOtherPlayer().getId()),
                        matchCreateRequest.getOtherPlayer().getUsername()
                ));
    }

    @Override
    public MatchCreateResponse toResponse(Match match) {
        return MatchCreateResponse.create(
                match.getId().getUuid(),
                new PlayerDto(match.getPlayer().getId().getUuid(), match.getPlayer().getUsername()),
                new PlayerDto(match.getOtherPlayer().getId().getUuid(), match.getOtherPlayer().getUsername()));
    }
}
