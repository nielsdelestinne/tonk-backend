package be.niedel.tonk.application.gamesession.getall;

import be.nielde.tonk.domain.gamesession.GameSession;
import be.nielde.tonk.domain.gamesession.GameSessionRepository;

import java.util.Collection;

public class GetAllGameSessionsUseCase {

    private final GameSessionRepository repository;

    public GetAllGameSessionsUseCase(GameSessionRepository repository) {
        this.repository = repository;
    }

    public Collection<GameSession> process() {
        return repository
                .getAll();
    }

}
