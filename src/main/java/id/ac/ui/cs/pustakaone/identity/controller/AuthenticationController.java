package id.ac.ui.cs.pustakaone.identity.controller;

import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationRequest;
import id.ac.ui.cs.pustakaone.identity.dto.AuthenticationResponse;
import id.ac.ui.cs.pustakaone.identity.dto.RegisterRequest;
import id.ac.ui.cs.pustakaone.identity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("")
    public ResponseEntity<AuthenticationResponse> getDetails(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
