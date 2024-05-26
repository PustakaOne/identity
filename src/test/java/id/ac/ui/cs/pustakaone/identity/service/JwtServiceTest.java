package id.ac.ui.cs.pustakaone.identity.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateAndExtractToken() {
        // Mock UserDetails
        when(userDetails.getUsername()).thenReturn("testUser");

        // Generate token
        String token = jwtService.generateToken(userDetails);

        // Extract username from token
        String extractedUsername = jwtService.extractUsername(token);

        // Assert
        assertEquals("testUser", extractedUsername);
    }

    @Test
    void testTokenValidity() {
        // Mock UserDetails
        when(userDetails.getUsername()).thenReturn("testUser");

        // Generate token
        String token = jwtService.generateToken(userDetails);

        // Token is valid if the username matches and it's not expired
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void testTokenExpiration() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Mock UserDetails
        when(userDetails.getUsername()).thenReturn("testUser");

        // Generate token with a short expiration time
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("extraInfo", "someExtraData");
        String token = jwtService.generateToken(userDetails, extraClaims);

        // Extract expiration date using reflection
        Method extractExpirationMethod = JwtService.class.getDeclaredMethod("extractExpiration", String.class);
        extractExpirationMethod.setAccessible(true);
        Date expirationDate = (Date) extractExpirationMethod.invoke(jwtService, token);

        // Token is expired if the expiration date is before the current date
        assertTrue(expirationDate.before(new Date(System.currentTimeMillis() + 61 * 60 * 1_000)));
    }
}
