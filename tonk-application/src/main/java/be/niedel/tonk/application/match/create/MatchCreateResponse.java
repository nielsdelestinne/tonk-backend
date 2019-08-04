package be.niedel.tonk.application.match.create;

public class MatchCreateResponse {

    private final String matchId;
    private final PlayerDto player;
    private final PlayerDto otherPlayer;

    private MatchCreateResponse() {
        matchId = null;
        player = null;
        otherPlayer = null;
    }

    private MatchCreateResponse(String matchId, PlayerDto player, PlayerDto otherPlayer) {
        this.matchId = matchId;
        this.player = player;
        this.otherPlayer = otherPlayer;
    }

    public static MatchCreateResponse create(String matchId, PlayerDto player, PlayerDto otherPlayer) {
        return new MatchCreateResponse(matchId, player, otherPlayer);
    }

    public String getMatchId() {
        return matchId;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public PlayerDto getOtherPlayer() {
        return otherPlayer;
    }
}
