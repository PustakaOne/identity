package id.ac.ui.cs.pustakaone.identity.dto;

import id.ac.ui.cs.pustakaone.identity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String photoUrl;
    private String bio;
    private String gender;
    private Date birthDate;
    private Role role;
}
