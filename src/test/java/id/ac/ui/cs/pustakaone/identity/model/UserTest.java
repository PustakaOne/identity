package id.ac.ui.cs.pustakaone.identity.model;

import id.ac.ui.cs.pustakaone.identity.Enum.Role;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        // Create a user instance
        User user = new User();
        user.setId(1L);
        user.setFullName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setPhoneNumber("123456789");
        user.setPhotoUrl("http://example.com/photo.jpg");
        user.setBio("Lorem ipsum");
        user.setGender("Male");
        user.setBirthDate(new Date());
        user.setRole(Role.USER);

        // Test user getters
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getFullName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("123456789", user.getPhoneNumber());
        assertEquals("http://example.com/photo.jpg", user.getPhotoUrl());
        assertEquals("Lorem ipsum", user.getBio());
        assertEquals("Male", user.getGender());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    public void testUserAuthorities() {
        // Create a user instance
        User user = new User();
        user.setRole(Role.USER);

        // Test getAuthorities method
        assertEquals(1, user.getAuthorities().size());
        assertEquals("USER", user.getAuthorities().iterator().next().getAuthority());
    }
}
