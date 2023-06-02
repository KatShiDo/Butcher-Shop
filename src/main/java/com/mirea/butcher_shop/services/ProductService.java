package com.mirea.butcher_shop.services;

import com.mirea.butcher_shop.models.Image;
import com.mirea.butcher_shop.models.Product;
import com.mirea.butcher_shop.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getByTitle(String title) {
        if (title != null) {
            return productRepository.findByTitle(title);
        }
        else {
            return productRepository.findAll();
        }
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
