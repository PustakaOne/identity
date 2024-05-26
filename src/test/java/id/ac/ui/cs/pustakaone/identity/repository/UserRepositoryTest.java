package id.ac.ui.cs.pustakaone.identity.repository;

import id.ac.ui.cs.pustakaone.identity.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {

        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);


        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));


        Optional<User> result = userRepository.findByEmail(email);

        assertTrue(result.isPresent());
        assertTrue(result.get().getEmail().equals(email));
    }

    @Test
    void testExistsUserByEmail() {

        String email = "test@example.com";

        when(userRepository.existsUserByEmail(email)).thenReturn(true);

        boolean result = userRepository.existsUserByEmail(email);

        assertTrue(result);
    }
}
