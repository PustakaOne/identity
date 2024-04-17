package id.ac.ui.cs.pustakaone.identity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {
    @GetMapping("/")
    public String getHello() {
        return "Hello identity!";
    }

    @GetMapping("/profile")
    public String getRetrieveProfile() {
        return "Hello retrieve profile!";
    }

    @GetMapping("/profile/{id}")
    public String patchUpdateProfile(@PathVariable int id) {
        return "Hello update profile for id: " + id;
    }
}