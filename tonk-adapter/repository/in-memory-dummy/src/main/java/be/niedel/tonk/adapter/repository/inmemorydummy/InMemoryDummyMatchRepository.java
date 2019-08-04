package be.niedel.tonk.adapter.repository.inmemorydummy;

import be.niedel.tonk.domain.match.Match;
import be.niedel.tonk.domain.match.MatchId;
import be.niedel.tonk.domain.match.MatchRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryDummyMatchRepository implements MatchRepository {

    private final Map<MatchId, Match> matchIdsPerMatch;

    public InMemoryDummyMatchRepository() {
        matchIdsPerMatch = new ConcurrentHashMap<>();
    }

    @Override
    public Match save(Match match) {
        matchIdsPerMatch.put(match.getId(), match);
        return match;
    }

    @Override
    public Match get(MatchId matchId) {
        return matchIdsPerMatch.get(matchId);
    }

    @Override
    public boolean remove(MatchId matchId) {
        return matchIdsPerMatch.remove(matchId) != null;
    }
}
