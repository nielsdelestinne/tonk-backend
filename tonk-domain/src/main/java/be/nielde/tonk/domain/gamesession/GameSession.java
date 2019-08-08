package be.nielde.tonk.domain.gamesession;

import java.util.UUID;

public class GameSession {

    private GameSessionId id;
    private Player player;

    public GameSession(GameSessionId id, String playerUsername) {
        this.id = id;
        this.player = Player.create(PlayerId.create(UUID.randomUUID().toString()), playerUsername);
    }

    public GameSessionId getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }
}
