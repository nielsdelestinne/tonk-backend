package be.niedel.tonk.domain.gamesession;

import java.util.Collection;

public interface GameSessionRepository {

    GameSession save(GameSession gameSession);

    boolean remove(GameSessionId gameSessionId);

    Collection<GameSession> getAll();

}
