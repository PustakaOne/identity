package id.ac.ui.cs.pustakaone.identity.dto;

import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationResponseTest {

    @Test
    void testGetterAndSetter() {

        AuthenticationResponse response = new AuthenticationResponse();

        response.setToken("dummyToken");

        assertEquals("dummyToken", response.getToken());
    }
}
