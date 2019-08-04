package be.niedel.tonk.domain.match;

public final class MatchId {

    private final String uuid;

    private MatchId(String uuid) {
        this.uuid = uuid;
    }

    public static MatchId create(String idAsUuid) {
        return new MatchId(idAsUuid);
    }

    public String getUuid() {
        return uuid;
    }
}
