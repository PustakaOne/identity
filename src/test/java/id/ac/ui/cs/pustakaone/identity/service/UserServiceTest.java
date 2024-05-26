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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetCurrentUser() {

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test@example.com", "password", new ArrayList<>()
        );
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        User user = new User(1L, "Test User", "test@example.com", "password", null, null, null, null, null, null);
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));


        User result = userService.getCurrentUser();

        assertEquals(user, result);
    }

    @Test
    void testGetCurrentUser_NoAuthentication() {

        SecurityContextHolder.clearContext();

        User result = userService.getCurrentUser();

        assertEquals(null, result);
    }

    @Test
    void testGetCurrentUser_AuthenticationPrincipalIsString() {

        Authentication authentication = new UsernamePasswordAuthenticationToken("anonymous", null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User result = userService.getCurrentUser();

        assertEquals(null, result);
    }

    @Test
    void testGetAllUsers() {

        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Test User 1", "test1@example.com", "password1", null, null, null, null, null, null));
        userList.add(new User(2L, "Test User 2", "test2@example.com", "password2", null, null, null, null, null, null));
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(userList, result);
    }
}
