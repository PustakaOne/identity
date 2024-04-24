package id.ac.ui.cs.pustakaone.identity.repository;

import id.ac.ui.cs.pustakaone.identity.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testFindByUsernameFound() {
        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .noTelp("1234567890")
                .password("password")
                .build();
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("testuser").orElse(null);
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    void testFindByUsernameNotFound() {
        User foundUser = userRepository.findByUsername("nonexistentuser").orElse(null);
        assertNull(foundUser);
    }
}
