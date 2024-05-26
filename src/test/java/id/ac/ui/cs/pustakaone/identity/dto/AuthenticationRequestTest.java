import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationRequestTest {

    @Test
    public void testGetterAndSetter() {
        // Create an instance of AuthenticationRequest
        AuthenticationRequest request = new AuthenticationRequest();

        // Set values using setter methods
        request.setEmail("test@example.com");
        request.setPassword("password123");

        // Test getter methods
        assertEquals("test@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }
}
