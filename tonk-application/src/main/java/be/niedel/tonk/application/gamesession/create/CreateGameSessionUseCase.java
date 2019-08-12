package be.niedel.tonk.application.gamesession.create;

import be.niedel.tonk.domain.gamesession.GameSession;
import be.niedel.tonk.domain.gamesession.GameSessionId;
import be.niedel.tonk.domain.gamesession.GameSessionRepository;

public class CreateGameSessionUseCase {

    private final GameSessionRepository repository;

    public CreateGameSessionUseCase(GameSessionRepository repository) {
        this.repository = repository;
    }

    public void process(GameSessionId gameSessionId, String playerUsername) {
        repository.save(new GameSession(gameSessionId, playerUsername));
    }

}
