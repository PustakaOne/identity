package id.ac.ui.cs.pustakaone.identity.controller;

import id.ac.ui.cs.pustakaone.identity.dto.UserDetailsResponse;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDetails() throws ParseException {
        User user = new User();
        user.setId(1L);
        user.setFullName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setPhoneNumber("123456789");
        user.setPhotoUrl("https://example.com/photo.jpg");
        user.setBio("Bio");
        user.setGender("Male");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = dateFormat.parse("1990-01-01");
        user.setBirthDate(birthDate);

        user.setRole(id.ac.ui.cs.pustakaone.identity.Enum.Role.valueOf("USER"));

        when(userService.getCurrentUser()).thenReturn(user);

        ResponseEntity<UserDetailsResponse> response = userController.getDetails();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UserDetailsResponse userDetails = response.getBody();
        assertNotNull(userDetails);
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
    void testGetAllProfiles() throws ParseException {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setFullName("John Doe");
        user1.setEmail("john@example.com");
        user1.setPassword("password");
        user1.setPhoneNumber("123456789");
        user1.setPhotoUrl("https://example.com/photo.jpg");
        user1.setBio("Bio");
        user1.setGender("Male");

        // Convert String to Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate1 = dateFormat.parse("1990-01-01");
        user1.setBirthDate(birthDate1);

        // Convert String to Enum Role
        user1.setRole(id.ac.ui.cs.pustakaone.identity.Enum.Role.valueOf("USER"));

        User user2 = new User();
        user2.setId(2L);
        user2.setFullName("Jane Smith");
        user2.setEmail("jane@example.com");
        user2.setPassword("password123");
        user2.setPhoneNumber("987654321");
        user2.setPhotoUrl("https://example.com/photo2.jpg");
        user2.setBio("Bio2");
        user2.setGender("Female");

        // Convert String to Date
        Date birthDate2 = dateFormat.parse("1992-02-02");
        user2.setBirthDate(birthDate2);

        // Convert String to Enum Role
        user2.setRole(id.ac.ui.cs.pustakaone.identity.Enum.Role.valueOf("ADMIN"));

        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        // Act
        ResponseEntity<List<UserDetailsResponse>> response = userController.getAllProfiles();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<UserDetailsResponse> userDetailsList = response.getBody();
        assertNotNull(userDetailsList);
        assertEquals(2, userDetailsList.size());

        UserDetailsResponse userDetails1 = userDetailsList.stream().filter(u -> u.getId().equals(user1.getId())).findFirst().orElse(null);
        assertNotNull(userDetails1);
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

        UserDetailsResponse userDetails2 = userDetailsList.stream().filter(u -> u.getId().equals(user2.getId())).findFirst().orElse(null);
        assertNotNull(userDetails2);
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