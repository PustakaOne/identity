package id.ac.ui.cs.pustakaone.identity.service;

import id.ac.ui.cs.pustakaone.identity.Enum.Role;
import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationRequest;
import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationResponse;
import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testRegister() {
        // Mock data
        RegisterRequest registerRequest = new RegisterRequest("John Doe", "john.doe@example.com", "password", "1234567890", null, null, "Male", null);
        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .password("encodedPassword")
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(Role.USER)
                .build();

        // Stubbing
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwtToken");

        // Call the method
        AuthenticationResponse response = authenticationService.register(registerRequest);

        // Verify the result
        assertEquals("jwtToken", response.getToken());
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    public void testAuthenticate() {
        // Mock data
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("john.doe@example.com", "password");
        User user = User.builder()
                .fullName("John Doe")
                .email(authenticationRequest.getEmail())
                .password("encodedPassword")
                .phoneNumber("1234567890")
                .role(Role.USER)
                .build();

        // Stubbing
        when(userRepository.findByEmail(authenticationRequest.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwtToken");

        // Call the method
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        // Verify the result
        assertEquals("jwtToken", response.getToken());
    }
}
