package be.niedel.tonk.domain.gamesession;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

}
