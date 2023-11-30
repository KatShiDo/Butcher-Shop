package com.mirea.butcher_shop.controllers;

import com.mirea.butcher_shop.domain.entities.User;
import com.mirea.butcher_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Principal principal, Model model) {
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "user/loginView";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "user/registrationView";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.create(user)) {
            model.addAttribute("errorMessage", "Пользователь с username: " + user.getUsername() + " уже существует");
            return "user/registrationView";
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "user/profileView";
    }
}
