package id.ac.ui.cs.pustakaone.identity.dto;

import id.ac.ui.cs.pustakaone.identity.Enum.Role;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDetailsResponseTest {

    @Test
    void testGetterAndSetter() {

        Date sampleDate = new Date();

        UserDetailsResponse response = new UserDetailsResponse();

        response.setId(1L);
        response.setFullName("John Doe");
        response.setEmail("john@example.com");
        response.setPassword("password123");
        response.setPhoneNumber("1234567890");
        response.setPhotoUrl("http://example.com/photo.jpg");
        response.setBio("Lorem ipsum dolor sit amet");
        response.setGender("male");
        response.setBirthDate(sampleDate);
        response.setRole(Role.ADMIN);


        assertEquals(1L, response.getId());
        assertEquals("John Doe", response.getFullName());
        assertEquals("john@example.com", response.getEmail());
        assertEquals("password123", response.getPassword());
        assertEquals("1234567890", response.getPhoneNumber());
        assertEquals("http://example.com/photo.jpg", response.getPhotoUrl());
        assertEquals("Lorem ipsum dolor sit amet", response.getBio());
        assertEquals("male", response.getGender());
        assertEquals(sampleDate, response.getBirthDate());
        assertEquals(Role.ADMIN, response.getRole());
    }
}
