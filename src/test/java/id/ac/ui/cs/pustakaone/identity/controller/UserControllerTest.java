package id.ac.ui.cs.pustakaone.identity.controller;

import id.ac.ui.cs.pustakaone.identity.controller.UserController;
import id.ac.ui.cs.pustakaone.identity.dto.UserDetailsResponse;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.Enum.Role;
import id.ac.ui.cs.pustakaone.identity.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetDetails() {
        // Mock authenticated user
        User user = new User();
        user.setId(1L);
        user.setFullName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setPhoneNumber("123456789");
        user.setPhotoUrl("http://example.com/photo.jpg");
        user.setBio("Bio of John Doe");
        user.setGender("Male");
        user.setBirthDate(new Date());
        user.setRole(Role.USER);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Call the method
        ResponseEntity<UserDetailsResponse> responseEntity = userController.getDetails();

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        UserDetailsResponse userDetails = responseEntity.getBody();
        assertEquals(user.getId(), userDetails.getId());
        assertEquals(user.getFullName(), userDetails.getFullName());
        assertEquals(user.getEmail(), userDetails.getEmail());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertEquals(user.getPhoneNumber(), userDetails.getPhoneNumber());
        assertEquals(user.getPhotoUrl(), userDetails.getPhotoUrl());
        assertEquals(user.getBio(), userDetails.getBio());
        assertEquals(user.getGender(), userDetails.getGender());
        assertEquals(user.getBirthDate(), userDetails.getBirthDate());
        assertEquals(user.getRole(), userDetails.getRole());
    }

    @Test
    public void testGetAllProfiles() {
        // Mock data
        User user1 = new User();
        user1.setId(1L);
        user1.setFullName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password");
        user1.setPhoneNumber("123456789");
        user1.setPhotoUrl("http://example.com/photo.jpg");
        user1.setBio("Bio of John Doe");
        user1.setGender("Male");
        user1.setBirthDate(new Date());
        user1.setRole(Role.USER);

        User user2 = new User();
        user2.setId(2L);
        user2.setFullName("Jane Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("password");
        user2.setPhoneNumber("987654321");
        user2.setPhotoUrl("http://example.com/photo.jpg");
        user2.setBio("Bio of Jane Doe");
        user2.setGender("Female");
        user2.setBirthDate(new Date());
        user2.setRole(Role.USER);

        // Mock UserService
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        // Call the method
        ResponseEntity<List<UserDetailsResponse>> responseEntity = userController.getAllProfiles();

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<UserDetailsResponse> userDetailsList = responseEntity.getBody();
        assertEquals(2, userDetailsList.size());

        UserDetailsResponse userDetails1 = userDetailsList.get(0);
        assertEquals(user1.getId(), userDetails1.getId());
        assertEquals(user1.getFullName(), userDetails1.getFullName());
        assertEquals(user1.getEmail(), userDetails1.getEmail());
        assertEquals(user1.getPassword(), userDetails1.getPassword());
        assertEquals(user1.getPhoneNumber(), userDetails1.getPhoneNumber());
        assertEquals(user1.getPhotoUrl(), userDetails1.getPhotoUrl());
        assertEquals(user1.getBio(), userDetails1.getBio());
        assertEquals(user1.getGender(), userDetails1.getGender());
        assertEquals(user1.getBirthDate(), userDetails1.getBirthDate());
        assertEquals(user1.getRole(), userDetails1.getRole());

        UserDetailsResponse userDetails2 = userDetailsList.get(1);
        assertEquals(user2.getId(), userDetails2.getId());
        assertEquals(user2.getFullName(), userDetails2.getFullName());
        assertEquals(user2.getEmail(), userDetails2.getEmail());
        assertEquals(user2.getPassword(), userDetails2.getPassword());
        assertEquals(user2.getPhoneNumber(), userDetails2.getPhoneNumber());
        assertEquals(user2.getPhotoUrl(), userDetails2.getPhotoUrl());
        assertEquals(user2.getBio(), userDetails2.getBio());
        assertEquals(user2.getGender(), userDetails2.getGender());
        assertEquals(user2.getBirthDate(), userDetails2.getBirthDate());
        assertEquals(user2.getRole(), userDetails2.getRole());
    }
}
