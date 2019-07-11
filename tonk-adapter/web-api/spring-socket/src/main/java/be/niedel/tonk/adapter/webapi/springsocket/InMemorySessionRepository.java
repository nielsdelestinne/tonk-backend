package be.niedel.tonk.adapter.webapi.springsocket;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemorySessionRepository {

    private final Map<String, String> sessionIdsPerUsername;

    public InMemorySessionRepository() {
        sessionIdsPerUsername = new ConcurrentHashMap<>();
    }

    public void add(String sessionId, String username) {
        sessionIdsPerUsername.put(sessionId, username);
    }

    public void remove(String sessionId) {
        sessionIdsPerUsername.remove(sessionId);
    }
}
