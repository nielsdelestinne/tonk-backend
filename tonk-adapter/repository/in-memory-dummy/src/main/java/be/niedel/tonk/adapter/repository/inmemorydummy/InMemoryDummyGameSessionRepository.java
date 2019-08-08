package be.niedel.tonk.adapter.repository.inmemorydummy;

import be.nielde.tonk.domain.gamesession.GameSession;
import be.nielde.tonk.domain.gamesession.GameSessionId;
import be.nielde.tonk.domain.gamesession.GameSessionRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryDummyGameSessionRepository implements GameSessionRepository {

    private final Map<GameSessionId, GameSession> gameSessionsIdperGameSession;

    public InMemoryDummyGameSessionRepository() {
        gameSessionsIdperGameSession = new ConcurrentHashMap<>();
    }


    @Override
    public GameSession save(GameSession gameSession) {
        gameSessionsIdperGameSession.put(gameSession.getId(), gameSession);
        return gameSession;
    }

    @Override
    public boolean remove(GameSessionId gameSessionId) {
        return gameSessionsIdperGameSession.remove(gameSessionId) != null;
    }

    @Override
    public Collection<GameSession> getAll() {
        return gameSessionsIdperGameSession.values();
    }
}
