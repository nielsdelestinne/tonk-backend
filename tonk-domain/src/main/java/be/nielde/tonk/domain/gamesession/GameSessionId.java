package be.nielde.tonk.domain.gamesession;

public class GameSessionId {

    private final String uuid;

    private GameSessionId(String uuid) {
        this.uuid = uuid;
    }

    public static GameSessionId create(String idAsUuid) {
        return new GameSessionId(idAsUuid);
    }

    public String getUuid() {
        return uuid;
    }

}
