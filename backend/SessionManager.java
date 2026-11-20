import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private static final long SESSION_TIMEOUT = 30 * 60 * 1000;

    private static final ConcurrentHashMap<String, UserSession> sessions =
            new ConcurrentHashMap<>();

    private static class UserSession {
        String username;
        long lastAccess;

        UserSession(String username) {
            this.username = username;
            this.lastAccess = System.currentTimeMillis();
        }
    }

    public static String createSession(String username) {

        byte[] randomBytes = new byte[32];
        new SecureRandom().nextBytes(randomBytes);

        String token = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);

        sessions.put(token, new UserSession(username));

        return token;
    }

    public static boolean isValid(String token) {

        if (token == null) {
            return false;
        }

        UserSession session = sessions.get(token);

        if (session == null) {
            return false;
        }

        long now = System.currentTimeMillis();

        if (now - session.lastAccess > SESSION_TIMEOUT) {
            sessions.remove(token);
            return false;
        }

        session.lastAccess = now;
        return true;
    }

    public static void logout(String token) {
        if (token != null) {
            sessions.remove(token);
        }
    }

    public static String getUsername(String token) {

        if (!isValid(token)) {
            return null;
        }

        return sessions.get(token).username;
    }
}