package id.ac.ui.cs.pustakaone.identity.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterRequestTest {

    @Test
    public void testGetterAndSetter() {
        // Create a sample date
        Date sampleDate = new Date();

        // Create an instance of RegisterRequest
        RegisterRequest request = new RegisterRequest();

        // Set values using setter methods
        request.setFullName("John Doe");
        request.setEmail("john@example.com");
        request.setPassword("password123");
        request.setPhoneNumber("1234567890");
        request.setPhotoUrl("http://example.com/photo.jpg");
        request.setBio("Lorem ipsum dolor sit amet");
        request.setGender("male");
        request.setBirthDate(sampleDate);

        // Test getter methods
        assertEquals("John Doe", request.getFullName());
        assertEquals("john@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
        assertEquals("1234567890", request.getPhoneNumber());
        assertEquals("http://example.com/photo.jpg", request.getPhotoUrl());
        assertEquals("Lorem ipsum dolor sit amet", request.getBio());
        assertEquals("male", request.getGender());
        assertEquals(sampleDate.getTime(), request.getBirthDate().getTime());
    }
}
