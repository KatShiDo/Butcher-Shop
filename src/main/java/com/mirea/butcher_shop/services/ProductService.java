package com.mirea.butcher_shop.services;

import com.mirea.butcher_shop.models.Image;
import com.mirea.butcher_shop.models.Product;
import com.mirea.butcher_shop.models.enums.TypeOfProduct;
import com.mirea.butcher_shop.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getBurgers() {
        List<Product> products = productRepository.findAll();
        List<Product> burgers = new ArrayList<>();
        for (Product product : products) {
            if (product.getType() == TypeOfProduct.BURGER) {
                burgers.add(product);
            }
        }
        return burgers;
    }

    public List<Product> getPizza() {
        List<Product> products = productRepository.findAll();
        List<Product> pizza = new ArrayList<>();
        for (Product product : products) {
            if (product.getType() == TypeOfProduct.PIZZA) {
                pizza.add(product);
            }
        }
        return pizza;
    }

    public List<Product> getSnacks() {
        List<Product> products = productRepository.findAll();
        List<Product> snacks = new ArrayList<>();
        for (Product product : products) {
            if (product.getType() == TypeOfProduct.SNACK) {
                snacks.add(product);
            }
        }
        return snacks;
    }

    public List<Product> getDrinks() {
        List<Product> products = productRepository.findAll();
        List<Product> drinks = new ArrayList<>();
        for (Product product : products) {
            if (product.getType() == TypeOfProduct.DRINK) {
                drinks.add(product);
            }
        }
        return drinks;
    }

    public void save(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1, image2, image3;

        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            image1.setProduct(product);
            product.addImage(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            image2.setProduct(product);
            product.addImage(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            image3.setProduct(product);
            product.addImage(image3);
        }

        log.info("Saving new Product with Title: {}", product.getTitle());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setContent(file.getBytes());
        return image;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
