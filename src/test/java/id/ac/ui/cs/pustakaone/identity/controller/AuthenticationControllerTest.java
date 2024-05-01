package id.ac.ui.cs.pustakaone.identity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.pustakaone.identity.dto.LoginResponse;
import id.ac.ui.cs.pustakaone.identity.dto.LoginUserRequest;
import id.ac.ui.cs.pustakaone.identity.dto.WebResponse;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.service.AuthenticationService;
import id.ac.ui.cs.pustakaone.identity.service.JwtService;
import id.ac.ui.cs.pustakaone.identity.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthenticationControllerTest {

    @Mock
    JwtService jwtService;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    ValidationService validationService;

    @InjectMocks
    AuthenticationController controller;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testLogin() throws Exception {
        // Given
        LoginUserRequest request = new LoginUserRequest("username", "password");
        User authenticatedUser = new User(); // Mock authenticated user
        String jwtToken = "mockedJWTToken";
        long expirationTime = 3600L; // Mock expiration time

        // Mock void methods
        doNothing().when(validationService).validate(request);

        // Mock method with return value
        when(authenticationService.authenticate(request)).thenReturn(authenticatedUser);
        when(jwtService.generateToken(authenticatedUser)).thenReturn(jwtToken);
        when(jwtService.getExpirationTime()).thenReturn(expirationTime);

        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.token").value(jwtToken))
                .andExpect(jsonPath("$.data.expiredIn").value(expirationTime));

        // Verify
        verify(validationService, times(1)).validate(request);
        verify(authenticationService, times(1)).authenticate(request);
        verify(jwtService, times(1)).generateToken(authenticatedUser);
        verify(jwtService, times(1)).getExpirationTime();
        verifyNoMoreInteractions(validationService, authenticationService, jwtService);
    }
}
