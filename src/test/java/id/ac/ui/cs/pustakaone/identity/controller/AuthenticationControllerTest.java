package id.ac.ui.cs.pustakaone.identity.controller;

import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationRequest;
import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    private RegisterRequest registerRequest;
    private AuthenticationRequest authenticationRequest;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        registerRequest = new RegisterRequest();

        authenticationRequest = new AuthenticationRequest();

        user = new User();
    }

    @Test
    void testRegister() {
        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(user);

        ResponseEntity<User> responseEntity = authenticationController.register(registerRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testAuthenticate() {
        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(user);

        ResponseEntity<User> responseEntity = authenticationController.authenticate(authenticationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testGetDetails() {
        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(user);

        ResponseEntity<User> responseEntity = authenticationController.getDetails(authenticationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }
}
