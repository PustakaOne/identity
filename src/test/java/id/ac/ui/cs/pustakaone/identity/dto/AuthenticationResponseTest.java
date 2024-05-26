import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationResponseTest {

    @Test
    public void testGetterAndSetter() {
        // Create an instance of AuthenticationResponse
        AuthenticationResponse response = new AuthenticationResponse();

        // Set token using setter method
        response.setToken("dummyToken");

        // Test getter method
        assertEquals("dummyToken", response.getToken());
    }
}
