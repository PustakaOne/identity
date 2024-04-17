package id.ac.ui.cs.pustakaone.identity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {
    @GetMapping("/")
    public String getHello() {
        return "Hello identity!";
    }

    @GetMapping("/auth/register")
    public String postRegister() {
        return "Hello register!";
    }

    @GetMapping("/auth/login")
    public String postLogin() {
        return "Hello login!";
    }

    @GetMapping("/auth/logout")
    public String postLogout() {
        return "Hello logout!";
    }
}