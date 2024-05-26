package id.ac.ui.cs.pustakaone.identity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String photoUrl;
    private String bio;
    private String gender;
    private Date birthDate;
}
