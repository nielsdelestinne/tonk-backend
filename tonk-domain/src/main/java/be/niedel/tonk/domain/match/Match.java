package be.niedel.tonk.domain.match;

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
}
