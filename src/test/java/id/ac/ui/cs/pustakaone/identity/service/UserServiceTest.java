package id.ac.ui.cs.pustakaone.identity.service;

import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        // Mocking the behavior of UserRepository
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John Doe", "john@example.com", "password", null, null, null, null, null, null));
        userList.add(new User(2L, "Jane Doe", "jane@example.com", "password", null, null, null, null, null, null));

        when(userRepository.findAll()).thenReturn(userList);

        // Calling the method to be tested
        List<User> result = userService.getAllUsers();

        // Asserting the result
        assertEquals(userList.size(), result.size());
        assertEquals(userList.get(0), result.get(0));
        assertEquals(userList.get(1), result.get(1));
    }

    @Test
    public void testFindByUsername() {
        // Mocking the behavior of UserRepository
        User user = new User(1L, "John Doe", "john@example.com", "password", null, null, null, null, null, null);

        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));

        // Calling the method to be tested
        Optional<User> result = userRepository.findByEmail("john@example.com");

        // Asserting the result
        assertEquals(user, result.orElse(null));
    }

}
