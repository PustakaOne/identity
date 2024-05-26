package id.ac.ui.cs.pustakaone.identity.repository;

import id.ac.ui.cs.pustakaone.identity.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        // Mocking data
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        // Mocking repository behavior
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Test the method
        Optional<User> result = userRepository.findByEmail(email);

        // Assertion
        assertTrue(result.isPresent());
        assertTrue(result.get().getEmail().equals(email));
    }

    @Test
    void testExistsUserByEmail() {
        // Mocking data
        String email = "test@example.com";

        // Mocking repository behavior
        when(userRepository.existsUserByEmail(email)).thenReturn(true);

        // Test the method
        boolean result = userRepository.existsUserByEmail(email);

        // Assertion
        assertTrue(result);
    }
}
