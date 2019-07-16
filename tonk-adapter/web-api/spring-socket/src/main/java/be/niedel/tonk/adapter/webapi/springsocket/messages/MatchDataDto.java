package be.niedel.tonk.adapter.webapi.springsocket.messages;

public class MatchDataDto {

    private String matchId;
    private String player;
    private double positionX;
    private double positionY;

    public String getMatchId() {
        return matchId;
    }

    public MatchDataDto setMatchId(String matchId) {
        this.matchId = matchId;
        return this;
    }

    public String getPlayer() {
        return player;
    }

    public MatchDataDto setPlayer(String player) {
        this.player = player;
        return this;
    }

    public double getPositionX() {
        return positionX;
    }

    public MatchDataDto setPositionX(double positionX) {
        this.positionX = positionX;
        return this;
    }

    public double getPositionY() {
        return positionY;
    }

    public MatchDataDto setPositionY(double positionY) {
        this.positionY = positionY;
        return this;
    }
}
