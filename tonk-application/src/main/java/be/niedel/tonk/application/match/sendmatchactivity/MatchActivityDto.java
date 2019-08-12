package be.niedel.tonk.application.match.sendmatchactivity;

// TODO: Refactor (player is still the username (should become id)),... to inspect!
public class MatchActivityDto {

    private final String matchId;
    private final String player;
    private final double positionX;
    private final double positionY;

    private MatchActivityDto() {
        matchId = null;
        player = null;
        positionX = 0;
        positionY = 0;
    }

    public MatchActivityDto(String matchId, String player, double positionX, double positionY) {
        this.matchId = matchId;
        this.player = player;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getPlayer() {
        return player;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

}
