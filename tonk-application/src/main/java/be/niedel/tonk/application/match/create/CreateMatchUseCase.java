package be.niedel.tonk.application.match.create;

import be.niedel.tonk.domain.gamesession.GameSessionRepository;
import be.niedel.tonk.domain.match.MatchRepository;

public class CreateMatchUseCase {

    private final MatchRepository matchRepository;
    private final GameSessionRepository gameSessionRepository;
    private final CreateMatchMapper mapper;

    public CreateMatchUseCase(MatchRepository matchRepository,
                              GameSessionRepository gameSessionRepository) {
        this.matchRepository = matchRepository;
        this.gameSessionRepository = gameSessionRepository;
        this.mapper = new CreateMatchMapper();
    }

    public MatchCreateResponse process(MatchCreateRequest request) {
        validateBothPlayersInTheMatchRequestHaveAGameSession(request);
        return mapper.toResponse(
                matchRepository.save(mapper.toDomain(request)));
    }

    private void validateBothPlayersInTheMatchRequestHaveAGameSession(MatchCreateRequest request) {
        if (gameSessionRepository.getAll()
                .stream()
                .map(gameSession -> gameSession.getPlayer().getUsername())
                .filter(playerUsername -> filterOnPlayerUsernameInMatch(request, playerUsername))
                .count() != 2) {
            throw new IllegalArgumentException("At least one player ("
                    + request.getPlayer().getId() + " or "
                    + request.getOtherPlayer().getId()
                    + ") did not have an existing Game Session...");
        }
    }

    // TODO: Should be PlayerId, but for now, the frontend does not know what PlayerId a player has...
    private boolean filterOnPlayerUsernameInMatch(MatchCreateRequest request, String playerUsername) {
        return playerUsername.equals(request.getPlayer().getUsername())
                || playerUsername.equals(request.getOtherPlayer().getUsername());
    }

}
