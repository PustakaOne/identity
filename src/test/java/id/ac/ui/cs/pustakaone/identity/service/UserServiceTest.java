package id.ac.ui.cs.pustakaone.identity.service;

import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
    public void testGetCurrentUser() {
        // Mock authentication
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test@example.com", "password", new ArrayList<>()
        );
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Mock userRepository
        User user = new User(1L, "Test User", "test@example.com", "password", null, null, null, null, null, null);
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Call the method to be tested
        User result = userService.getCurrentUser();

        // Verify the result
        assertEquals(user, result);
    }

    @Test
    public void testGetCurrentUser_NoAuthentication() {
        // Ensure there's no authentication set
        SecurityContextHolder.clearContext();

        // Call the method to be tested
        User result = userService.getCurrentUser();

        // Verify the result is null
        assertEquals(null, result);
    }

    @Test
    public void testGetCurrentUser_AuthenticationPrincipalIsString() {
        // Mock authentication with principal as string
        Authentication authentication = new UsernamePasswordAuthenticationToken("anonymous", null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Call the method to be tested
        User result = userService.getCurrentUser();

        // Verify the result is null
        assertEquals(null, result);
    }

    @Test
    public void testGetAllUsers() {
        // Mock userRepository
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Test User 1", "test1@example.com", "password1", null, null, null, null, null, null));
        userList.add(new User(2L, "Test User 2", "test2@example.com", "password2", null, null, null, null, null, null));
        when(userRepository.findAll()).thenReturn(userList);

        // Call the method to be tested
        List<User> result = userService.getAllUsers();

        // Verify the result
        assertEquals(userList, result);
    }
}
