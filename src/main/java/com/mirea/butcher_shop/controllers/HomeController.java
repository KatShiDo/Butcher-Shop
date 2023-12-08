package com.mirea.butcher_shop.controllers;

import com.mirea.butcher_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "indexView";
    }
}
