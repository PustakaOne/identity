package id.ac.ui.cs.pustakaone.identity.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testBuilder() {
        // Arrange
        String email = "john@example.com";
        String noTelp = "08123456789";
        String password = "password123";
        byte[] foto = {1, 2, 3};
        String jenisKelamin = "Laki-laki";
        LocalDate tanggalLahir = LocalDate.of(1990, 1, 1);
        String bio = "Lorem ipsum dolor sit amet";
        String role = "pelanggan"; // default role
        String username = "john_doe";

        // Act
        User user = new User.Builder()
                .email(email)
                .noTelp(noTelp)
                .password(password)
                .foto(foto)
                .jenisKelamin(jenisKelamin)
                .tanggalLahir(tanggalLahir)
                .bio(bio)
                .role(role)
                .username(username)
                .build();

        // Assert
        assertEquals(email, user.getEmail());
        assertEquals(noTelp, user.getNoTelp());
        assertEquals(password, user.getPassword());
        assertEquals(foto, user.getFoto());
        assertEquals(jenisKelamin, user.getJenisKelamin());
        assertEquals(tanggalLahir, user.getTanggalLahir());
        assertEquals(bio, user.getBio());
        assertEquals(role, user.getRole());
        assertEquals(username, user.getUsername());
    }
}
