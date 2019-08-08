package be.niedel.tonk.application.gamesession.remove;

import be.nielde.tonk.domain.gamesession.GameSessionId;
import be.nielde.tonk.domain.gamesession.GameSessionRepository;

public class RemoveGameSessionUseCase {

    private final GameSessionRepository repository;

    public RemoveGameSessionUseCase(GameSessionRepository repository) {
        this.repository = repository;
    }

    public boolean process(GameSessionId gameSessionId) {
        return repository.remove(gameSessionId);
    }

}
