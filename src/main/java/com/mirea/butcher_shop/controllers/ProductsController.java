package com.mirea.butcher_shop.controllers;

import com.mirea.butcher_shop.domain.entities.Product;
import com.mirea.butcher_shop.domain.entities.User;
import com.mirea.butcher_shop.services.ProductService;
import com.mirea.butcher_shop.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/burgers")
    public String productsBurgers(Principal principal, Model model) {
        model.addAttribute("products", productService.getBurgers());
        model.addAttribute("typeOfProduct", "Бургеры");
        User user = userService.getByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("userProducts", user.getProducts());
        return "products/productsView";
    }

    @GetMapping("/pizza")
    public String productsPizza(Principal principal, Model model) {
        model.addAttribute("products", productService.getPizza());
        model.addAttribute("typeOfProduct", "Пицца");
        User user = userService.getByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("userProducts", user.getProducts());
        return "products/productsView";
    }

    @GetMapping("/snacks")
    public String productsSnacks(Principal principal, Model model) {
        model.addAttribute("products", productService.getSnacks());
        model.addAttribute("typeOfProduct", "Закуски");
        User user = userService.getByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("userProducts", user.getProducts());
        return "products/productsView";
    }

    @GetMapping("/drinks")
    public String productsDrinks(Principal principal, Model model) {
        model.addAttribute("products", productService.getDrinks());
        model.addAttribute("typeOfProduct", "Напитки");
        User user = userService.getByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("userProducts", user.getProducts());
        return "products/productsView";
    }

    @Transactional
    @GetMapping("/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "products/productInfoView";
    }
}
