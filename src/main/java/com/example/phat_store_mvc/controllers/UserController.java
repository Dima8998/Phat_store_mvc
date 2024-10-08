package com.example.phat_store_mvc.controllers;

import com.example.phat_store_mvc.model.security.ApplicationUser;
import com.example.phat_store_mvc.repositories.ApplicationUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepo appUserRepo;

    @PostMapping("/registration")
    public String registration(@RequestParam String email, @RequestParam String password) {
        ApplicationUser applicationUser = new ApplicationUser(email, passwordEncoder.encode(password));
        appUserRepo.save(applicationUser);
        return "redirect:/";
    }
}
