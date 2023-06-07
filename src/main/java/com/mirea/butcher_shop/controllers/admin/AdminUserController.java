package com.mirea.butcher_shop.controllers.admin;

import com.mirea.butcher_shop.models.Product;
import com.mirea.butcher_shop.models.User;
import com.mirea.butcher_shop.models.enums.Role;
import com.mirea.butcher_shop.services.ProductService;
import com.mirea.butcher_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminUserController {

    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.ban(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("targetUser", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "admin/userEditView";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeRoles(user, form);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("targetUser", user);
        Set<Product> set = new HashSet<>(user.getProducts());
        List<Product> products = new ArrayList<>(set);
        model.addAttribute("products", products);
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "admin/userInfoView";
    }
}
