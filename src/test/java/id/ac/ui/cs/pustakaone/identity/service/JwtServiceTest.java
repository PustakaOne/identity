package id.ac.ui.cs.pustakaone.identity.service;

import id.ac.ui.cs.pustakaone.identity.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    JwtService jwtService;

    User userLogin = User.builder()
            .email("abc@gmail.com")
            .username("username")
            .bio("bio")
            .password("password")
            .build();

    @Test
    void extractUsernameTest() {
        String token = jwtService.generateToken(userLogin);
        assertEquals("abc@gmail.com", jwtService.extractUsername(token));
    }

    @Test
    void testGenerateTokenTest() {
        String token = jwtService.generateToken(userLogin);
        assertNotNull(token);
    }

    @Test
    void getExpirationTimeTest() {
        assertEquals(3000L, jwtService.getExpirationTime());
    }

    @Test
    void isTokenValidTestSuccess() {
        String token = jwtService.generateToken(userLogin);
        assertTrue(jwtService.isTokenValid(token, userLogin));
    }

    @Test
    void isTokenValidTestNotSameUser() {
        User other = User.builder().email("other@gmail.com").build();
        String token = jwtService.generateToken(userLogin);
        assertFalse(jwtService.isTokenValid(token, other));
    }

    @Test
    void isTokenValidTestTimeExceed() throws InterruptedException {
        String token = jwtService.generateToken(userLogin);
        Thread.sleep(3000);
        assertThrows(ExpiredJwtException.class, () -> jwtService.isTokenValid(token, userLogin));
    }
}