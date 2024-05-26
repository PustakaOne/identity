package id.ac.ui.cs.pustakaone.identity.configure;

import id.ac.ui.cs.pustakaone.identity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ApplicationConfigTest {

    @Test
    void testUserDetailsServiceBean() {
        UserRepository userRepository = mock(UserRepository.class);
        ApplicationConfig applicationConfig = new ApplicationConfig(userRepository);
        UserDetailsService userDetailsService = applicationConfig.userDetailsService();

        assertNotNull(userDetailsService);
    }

    @Test
    void testAuthenticationProviderBean() {
        UserRepository userRepository = mock(UserRepository.class);
        ApplicationConfig applicationConfig = new ApplicationConfig(userRepository);
        AuthenticationProvider authenticationProvider = applicationConfig.authenticationProvider();

        assertNotNull(authenticationProvider);
    }

    @Test
    void testPasswordEncoderBean() {
        UserRepository userRepository = mock(UserRepository.class);
        ApplicationConfig applicationConfig = new ApplicationConfig(userRepository);
        PasswordEncoder passwordEncoder = applicationConfig.passwordEncoder();

        assertNotNull(passwordEncoder);
    }
}
