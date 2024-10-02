package com.example.phat_store_mvc.controllers;

import com.example.phat_store_mvc.dao.services.CategoryService;
import com.example.phat_store_mvc.model.entities.stock.dto.CategoryDTO;
import com.example.phat_store_mvc.model.security.ApplicationUser;
import com.example.phat_store_mvc.repositories.ApplicationUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
