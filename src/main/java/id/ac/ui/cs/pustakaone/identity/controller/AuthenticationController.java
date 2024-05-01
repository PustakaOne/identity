package id.ac.ui.cs.pustakaone.identity.controller;

import id.ac.ui.cs.pustakaone.identity.dto.LoginResponse;
import id.ac.ui.cs.pustakaone.identity.dto.LoginUserRequest;
import id.ac.ui.cs.pustakaone.identity.dto.WebResponse;
import id.ac.ui.cs.pustakaone.identity.model.User;
import id.ac.ui.cs.pustakaone.identity.service.AuthenticationService;
import id.ac.ui.cs.pustakaone.identity.service.JwtService;
import id.ac.ui.cs.pustakaone.identity.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("api/auth/")
public class AuthenticationController {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ValidationService validationService;

    public class HomeController {
        @GetMapping("/")
        public String getHello() {
            return "Hello identity!";
        }

//        @PostMapping("/auth/register")
//        public String postRegister() {
//            return "Hello register!";
//        }
//
////    @PostMapping("/auth/login")
////    public String postLogin() {
////        return "Hello login!";
////    }
//
//        @PostMapping("/auth/logout")
//        public String postLogout() {
//            return "Hello logout!";
//        }

        @PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public WebResponse<LoginResponse> login(@RequestBody LoginUserRequest request){
            //contraint violation exception
            validationService.validate(request);
            //bisa ada bad concern error
            User authenticatedUser = authenticationService.authenticate(request);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiredIn(jwtService.getExpirationTime());

            return WebResponse.<LoginResponse>builder().data(loginResponse).build();

        }
    }
}
