package be.niedel.tonk.adapter.webapi.springsocket.messages;

public class MatchDataDto {

    private String matchId;
    private MatchDataForPlayerDto playerDataDto;
    private MatchDataForPlayerDto otherPlayerDataDto;

    public MatchDataDto(String matchId, MatchDataForPlayerDto playerDataDto, MatchDataForPlayerDto otherPlayerDataDto) {
        this.matchId = matchId;
        this.playerDataDto = playerDataDto;
        this.otherPlayerDataDto = otherPlayerDataDto;
    }

    public String getMatchId() {
        return matchId;
    }

    public MatchDataDto setMatchId(String matchId) {
        this.matchId = matchId;
        return this;
    }

    public MatchDataForPlayerDto getPlayerDataDto() {
        return playerDataDto;
    }

    public MatchDataDto setPlayerDataDto(MatchDataForPlayerDto playerDataDto) {
        this.playerDataDto = playerDataDto;
        return this;
    }

    public MatchDataForPlayerDto getOtherPlayerDataDto() {
        return otherPlayerDataDto;
    }

    public MatchDataDto setOtherPlayerDataDto(MatchDataForPlayerDto otherPlayerDataDto) {
        this.otherPlayerDataDto = otherPlayerDataDto;
        return this;
    }
}
