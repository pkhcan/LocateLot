package data_access;
import java.util.UUID;


public class SessionTokenGen {

    public static String generateSessionToken() {
        // Generate a random UUID (Universally Unique Identifier)
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
