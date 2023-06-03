package com.mirea.butcher_shop.controllers;

import com.mirea.butcher_shop.models.Product;
import com.mirea.butcher_shop.services.ProductService;
import com.mirea.butcher_shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/products")
    public String products(@RequestParam(name = "searchWord", required = false) String searchWord, Principal principal, Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "products/productsView";
    }

    @GetMapping("/products/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        model.addAttribute("user", userService.getByPrincipal(principal));
        return "products/productInfoView";
    }
}
