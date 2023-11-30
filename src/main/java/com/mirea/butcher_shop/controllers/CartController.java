package com.mirea.butcher_shop.controllers;

import com.mirea.butcher_shop.domain.entities.Product;
import com.mirea.butcher_shop.domain.entities.User;
import com.mirea.butcher_shop.services.ProductService;
import com.mirea.butcher_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_USER')")
public class CartController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public String cart(Principal principal, Model model) {
        User user = userService.getByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "cart/cartView";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, Principal principal) {
        User user = userService.getByPrincipal(principal);
        Product product = productService.getById(id);
        if (!user.getProducts().contains(product)) {
            user.addProduct(product);
        }
        userService.update(user);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable Long id, Principal principal) {
        User user = userService.getByPrincipal(principal);
        Product product = productService.getById(id);
        user.deleteProduct(product);
        userService.update(user);
        return "redirect:/cart";
    }
}
