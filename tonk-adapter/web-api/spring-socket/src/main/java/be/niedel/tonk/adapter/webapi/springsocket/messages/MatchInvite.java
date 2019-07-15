package be.niedel.tonk.adapter.webapi.springsocket.messages;

public class MatchInvite {

    private String matchId;
    private String player;
    private String otherPlayer;

    public String getMatchId() {
        return matchId;
    }

    public MatchInvite setMatchId(String matchId) {
        this.matchId = matchId;
        return this;
    }

    public String getPlayer() {
        return player;
    }

    public MatchInvite setPlayer(String player) {
        this.player = player;
        return this;
    }

    public String getOtherPlayer() {
        return otherPlayer;
    }

    public MatchInvite setOtherPlayer(String otherPlayer) {
        this.otherPlayer = otherPlayer;
        return this;
    }
}
