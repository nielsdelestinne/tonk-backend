package be.niedel.tonk.domain.match;

public interface MatchRepository {

    boolean add(MatchId matchId, Match match);

    boolean remove(MatchId matchId);

    boolean get(MatchId matchId);

}
