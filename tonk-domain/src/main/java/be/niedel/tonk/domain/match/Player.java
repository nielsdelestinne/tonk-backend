package be.niedel.tonk.domain.match;

public final class Player {

    private final PlayerId id;
    private final String username;

    private Player(PlayerId id, String username) {
        this.id = id;
        this.username = username;
    }

    public static Player create(PlayerId playerId, String username) {
        return new Player(playerId, username);
    }

    public PlayerId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
