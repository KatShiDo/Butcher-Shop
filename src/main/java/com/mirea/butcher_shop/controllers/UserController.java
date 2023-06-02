package com.mirea.butcher_shop.controllers;

import com.mirea.butcher_shop.models.User;
import com.mirea.butcher_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "user/loginView";
    }

    @GetMapping("/registration")
    public String registration() {
        return "user/registrationView";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.create(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "user/registrationView";
        }
        userService.create(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user/userInfoView";
    }
}