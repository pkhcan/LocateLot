package data_access;
import java.util.UUID;


class SessionTokenGen {

    /**
     * Generate a random UUID (Universally Unique Identifier) for SessionToken
     * @return session token in String
     */
    public static String generateSessionToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
