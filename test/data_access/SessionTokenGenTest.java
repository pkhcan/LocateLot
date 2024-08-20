package data_access;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTokenGenTest {
    /**
     * Check that the token generator returns a String
     */
    @Test
    void testTokenGenerator(){
        assertInstanceOf(String.class, SessionTokenGen.generateSessionToken());
    }
}
