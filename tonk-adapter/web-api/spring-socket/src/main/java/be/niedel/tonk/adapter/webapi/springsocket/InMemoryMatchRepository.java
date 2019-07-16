package be.niedel.tonk.adapter.webapi.springsocket;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryMatchRepository {

    private final Map<String, MatchData> matchIdsPerMatchData;

    public InMemoryMatchRepository() {
        matchIdsPerMatchData = new ConcurrentHashMap<>();
    }

    public void add(String matchId, MatchData matchData) {
        matchIdsPerMatchData.put(matchId, matchData);
    }

    public void remove(String matchId) {
        matchIdsPerMatchData.remove(matchId);
    }

    public void get(String matchId) {
        matchIdsPerMatchData.get(matchId);
    }

    public static class MatchData {
        private String player;
        private String otherPlayer;

        public String getPlayer() {
            return player;
        }

        public MatchData setPlayer(String player) {
            this.player = player;
            return this;
        }

        public String getOtherPlayer() {
            return otherPlayer;
        }

        public MatchData setOtherPlayer(String otherPlayer) {
            this.otherPlayer = otherPlayer;
            return this;
        }
    }
}