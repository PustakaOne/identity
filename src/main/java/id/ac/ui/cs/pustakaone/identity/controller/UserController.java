package id.ac.ui.cs.pustakaone.identity.controller;

import id.ac.ui.cs.pustakaone.identity.dto.UserDetailsResponse;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDetailsResponse> getDetails() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(
                UserDetailsResponse
                        .builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .photoUrl(user.getPhotoUrl())
                        .bio(user.getBio())
                        .gender(user.getGender())
                        .birthDate(user.getBirthDate())
                        .role(user.getRole())
                        .build());
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<UserDetailsResponse>> getAllProfiles() {
        List<User> allUsers = userService.getAllUsers();
        List<UserDetailsResponse> userProfiles = allUsers.stream()
                .map(user -> UserDetailsResponse.builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .photoUrl(user.getPhotoUrl())
                        .bio(user.getBio())
                        .gender(user.getGender())
                        .birthDate(user.getBirthDate())
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(userProfiles);
    }
}
