package be.niedel.tonk.adapter.webapi.springsocket.messages;

public class MatchDataForPlayerDto {
    private String player;
    private double positionX;
    private double positionY;

    public MatchDataForPlayerDto(String player, double positionX, double positionY) {
        this.player = player;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getPlayer() {
        return player;
    }

    public MatchDataForPlayerDto setPlayer(String player) {
        this.player = player;
        return this;
    }

    public double getPositionX() {
        return positionX;
    }

    public MatchDataForPlayerDto setPositionX(double positionX) {
        this.positionX = positionX;
        return this;
    }

    public double getPositionY() {
        return positionY;
    }

    public MatchDataForPlayerDto setPositionY(double positionY) {
        this.positionY = positionY;
        return this;
    }
}
