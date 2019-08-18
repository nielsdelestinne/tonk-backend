package be.niedel.tonk.application.match.create;

public class MatchCreateRequest {

    private final PlayerDto player;
    private final PlayerDto otherPlayer;

    private MatchCreateRequest() {
        player = null;
        otherPlayer = null;
    }

    public MatchCreateRequest(PlayerDto player, PlayerDto otherPlayer) {
        this.player = player;
        this.otherPlayer = otherPlayer;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public PlayerDto getOtherPlayer() {
        return otherPlayer;
    }
}
