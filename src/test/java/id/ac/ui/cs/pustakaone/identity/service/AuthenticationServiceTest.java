package id.ac.ui.cs.pustakaone.identity.service;

import id.ac.ui.cs.pustakaone.identity.Enum.Role;
import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationRequest;
import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    private RegisterRequest registerRequest;
    private AuthenticationRequest authenticationRequest;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        registerRequest = new RegisterRequest();
        registerRequest.setFullName("John Doe");
        registerRequest.setEmail("johndoe@example.com");
        registerRequest.setBio("Bio");
        registerRequest.setPassword("password");
        registerRequest.setGender("Male");
        registerRequest.setPhotoUrl("http://example.com/photo.jpg");
        registerRequest.setBirthDate(new Date()); // Menggunakan objek Date
        registerRequest.setPhoneNumber("123456789");

        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("johndoe@example.com");
        authenticationRequest.setPassword("password");

        user = User.builder()
                .fullName("John Doe")
                .email("johndoe@example.com")
                .bio("Bio")
                .password("encoded_password")
                .gender("Male")
                .photoUrl("http://example.com/photo.jpg")
                .birthDate(new Date()) // Menggunakan objek Date
                .phoneNumber("123456789")
                .role(Role.USER)
                .build();
    }

    @Test
    void testRegister() {
        when(passwordEncoder.encode(any(String.class))).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = authenticationService.register(registerRequest);

        assertEquals(user, registeredUser);
    }

    @Test
    void testAuthenticate() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);

        User authenticatedUser = authenticationService.authenticate(authenticationRequest);

        assertEquals(user, authenticatedUser);
    }
}
