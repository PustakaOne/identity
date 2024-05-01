package id.ac.ui.cs.pustakaone.identity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "no_telp"),
})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(name = "no_telp", unique = true)
    private String noTelp;

    @NotBlank
    private String password;

    @Lob
    private byte[] foto;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    private String bio;

    private String role;

    private String username;

    // Constructor with Builder as parameter
    public User(Builder builder) {
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
        this.noTelp = builder.noTelp;
        this.role = builder.role;
        this.foto = builder.foto;
        this.jenisKelamin = builder.jenisKelamin;
        this.tanggalLahir = builder.tanggalLahir;
        this.bio = builder.bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // Method to return the username
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUsernameReal() {
        return username;
    }


    // Class builder for creating User objects with optional attributes
    public static class Builder {
        private String email;
        private String noTelp;
        private String password;
        private byte[] foto;
        private String jenisKelamin;
        private LocalDate tanggalLahir;
        private String bio;
        private String role;
        private String username;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder noTelp(String noTelp) {
            this.noTelp = noTelp;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder foto(byte[] foto) {
            this.foto = foto;
            return this;
        }

        public Builder jenisKelamin(String jenisKelamin) {
            this.jenisKelamin = jenisKelamin;
            return this;
        }

        public Builder tanggalLahir(LocalDate tanggalLahir) {
            this.tanggalLahir = tanggalLahir;
            return this;
        }

        public Builder bio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder role(String role) {
            this.role = "pelanggan";
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public User build() {
            User user = new User();
            user.setEmail(this.email);
            user.setNoTelp(this.noTelp);
            user.setPassword(this.password);
            user.setFoto(this.foto);
            user.setJenisKelamin(this.jenisKelamin);
            user.setTanggalLahir(this.tanggalLahir);
            user.setBio(this.bio);
            user.setRole(this.role);
            user.setUsername(this.username);
            return user;
        }
    }
    public User(String username, String email, String noTelp, String password) {
        this.username = username;
        this.email = email;
        this.noTelp = noTelp;
        this.password = password;
        this.role = "pelanggan";
    }
}
