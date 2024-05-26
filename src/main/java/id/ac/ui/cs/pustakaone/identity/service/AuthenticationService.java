package id.ac.ui.cs.pustakaone.identity.service;

import id.ac.ui.cs.pustakaone.identity.Enum.Role;
import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationRequest;
import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationResponse;
import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequest request){
        var user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .bio(request.getBio())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(request.getGender())
                .photoUrl(request.getPhotoUrl())
                .birthDate(request.getBirthDate())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return user;
    }

    public User authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
;
        return user;
    }

}
