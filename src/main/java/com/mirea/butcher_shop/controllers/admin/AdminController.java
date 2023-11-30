package com.mirea.butcher_shop.controllers.admin;

import com.mirea.butcher_shop.domain.entities.Product;
import com.mirea.butcher_shop.domain.entities.User;
import com.mirea.butcher_shop.domain.enums.Role;
import com.mirea.butcher_shop.services.ProductService;
import com.mirea.butcher_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping()
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "admin/adminView";
    }

    @PostMapping("/products/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                Product product) throws IOException {
        productService.save(product, file1, file2, file3);
        return "redirect:/admin";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.ban(id);
        return "redirect:/admin";
    }

    @GetMapping("/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("targetUser", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "admin/userEditView";
    }

    @PostMapping("/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeRoles(user, form);
        return "redirect:/admin";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("targetUser", user);
        Set<Product> set = new HashSet<>(user.getProducts());
        List<Product> products = new ArrayList<>(set);
        model.addAttribute("products", products);
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "admin/userInfoView";
    }
}
