package be.niedel.tonk.application.lobby.sendmessage;

public class LobbyMessageDto {

    private final String fromPlayerUsername;
    private final String actualMessage;

    private LobbyMessageDto() {
        fromPlayerUsername = null;
        actualMessage = null;
    }

    public LobbyMessageDto(String fromPlayerUsername, String actualMessage) {
        this.fromPlayerUsername = fromPlayerUsername;
        this.actualMessage = actualMessage;
    }

    public String getFromPlayerUsername() {
        return fromPlayerUsername;
    }

    public String getActualMessage() {
        return actualMessage;
    }
}
