package be.niedel.tonk.domain.match;

import static java.util.UUID.randomUUID;

public final class Player {

    private final PlayerId id;
    private final String username;

    private Player(PlayerId id, String username) {
        this.id = id;
        this.username = username;
    }

    public static Player create(String username) {
        return new Player(PlayerId.create(randomUUID().toString()), username);
    }
}
