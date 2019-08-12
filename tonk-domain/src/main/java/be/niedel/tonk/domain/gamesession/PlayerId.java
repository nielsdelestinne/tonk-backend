package be.niedel.tonk.domain.gamesession;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerId playerId = (PlayerId) o;
        return Objects.equals(uuid, playerId.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
