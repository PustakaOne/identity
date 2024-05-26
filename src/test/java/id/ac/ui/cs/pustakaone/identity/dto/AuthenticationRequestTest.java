package id.ac.ui.cs.pustakaone.identity.dto;

import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationRequestTest {

    @Test
    void testGetterAndSetter() {

        AuthenticationRequest request = new AuthenticationRequest();


        request.setEmail("test@example.com");
        request.setPassword("password123");

        assertEquals("test@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }
}
