package be.niedel.tonk.domain.match;

import be.niedel.tonk.domain.gamesession.Player;

import java.util.Objects;

import static java.util.UUID.randomUUID;

public final class Match {

    private final MatchId id;
    private final Player player;
    private final Player otherPlayer;

    private Match(MatchId id, Player player, Player otherPlayer) {
        this.id = id;
        this.player = player;
        this.otherPlayer = otherPlayer;
    }

    public static Match create(Player player, Player otherPlayer) {
        return new Match(MatchId.create(randomUUID().toString()), player, otherPlayer);
    }

    public MatchId getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id) &&
                Objects.equals(player, match.player) &&
                Objects.equals(otherPlayer, match.otherPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player, otherPlayer);
    }

}
