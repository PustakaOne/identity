package id.ac.ui.cs.pustakaone.identity.controller;

import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationRequest;
import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationResponse;
import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    public void testRegister() {
        // Mock data
        RegisterRequest registerRequest = RegisterRequest.builder()
                .fullName("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        // Stubbing
        when(authenticationService.register(registerRequest)).thenReturn(new AuthenticationResponse("jwtToken"));

        // Call the method
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("jwtToken", responseEntity.getBody().getToken());
    }

    @Test
    public void testAuthenticate() {
        // Mock data
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("john.doe@example.com", "password");

        // Stubbing
        when(authenticationService.authenticate(authenticationRequest)).thenReturn(new AuthenticationResponse("jwtToken"));

        // Call the method
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(authenticationRequest);

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("jwtToken", responseEntity.getBody().getToken());
    }
}
