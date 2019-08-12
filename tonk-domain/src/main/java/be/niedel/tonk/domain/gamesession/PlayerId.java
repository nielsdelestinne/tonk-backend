package be.niedel.tonk.domain.gamesession;

public class PlayerId {

    private final String uuid;

    private PlayerId(String uuid) {
        this.uuid = uuid;
    }

    public static PlayerId create(String idAsUuid) {
        return new PlayerId(idAsUuid);
    }

    public String getUuid() {
        return uuid;
    }

}
