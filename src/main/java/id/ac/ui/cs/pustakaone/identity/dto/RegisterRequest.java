package id.ac.ui.cs.pustakaone.identity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String username;
    private String noTelp;
    private byte[] foto;
    private String jenisKelamin;
    private LocalDate tanggalLahir;
    private String bio;

    private String role;

}
