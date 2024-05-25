package id.ac.ui.cs.pustakaone.identity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import id.ac.ui.cs.pustakaone.identity.enums.EnumRole;
import id.ac.ui.cs.pustakaone.identity.enums.EnumJenisKelamin;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "Template_User", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Template_User")
    private Integer id;

    @NotBlank
    @Size(max = 128)
    private String fullName;

    @NotBlank
    @Size(max = 128)
    private String password;

    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 256)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 64)
    @Column(name = "no_telp", unique = true)
    private String noTelp;

    private String foto;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    private String bio;

    private String role;

    @OneToMany(mappedBy = "user")
    private transient List<Token> tokens;
    private boolean active;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if ("ADMIN".equals(role)) {
            return ApplicationUserRole.ADMIN.getGrantedAuthority();
        } else {
            return ApplicationUserRole.USER.getGrantedAuthority();
        }
    }

    public void setJenisKelamin(String jenisKelamin) throws IllegalArgumentException {
        if (jenisKelamin == null) {
            throw new IllegalArgumentException("Gender is Empty");
        }

        if (!EnumJenisKelamin.contains(jenisKelamin)) {
            throw new IllegalArgumentException("Gender is not valid");
        }

        this.jenisKelamin = jenisKelamin;
    }

    public void setRole(String role) throws IllegalArgumentException {

        if (role == null) {
            throw new IllegalArgumentException("Role is Empty");
        }

        if (!EnumRole.contains(role)) {
            throw new IllegalArgumentException("Role is not valid");
        }

        this.role = role;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
