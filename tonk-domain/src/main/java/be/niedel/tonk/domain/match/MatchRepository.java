package be.niedel.tonk.domain.match;

public interface MatchRepository {

    Match save(Match match);

    Match get(MatchId matchId);

    boolean remove(MatchId matchId);


}
