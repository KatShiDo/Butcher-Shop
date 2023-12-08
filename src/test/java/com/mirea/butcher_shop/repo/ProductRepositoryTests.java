package com.mirea.butcher_shop.repo;

import com.mirea.butcher_shop.domain.entities.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void ProductRepository_Save_ReturnsSavedProduct() {

        var product = Product
                .builder()
                .title("product1")
                .price(1000)
                .build();

        var savedProduct = productRepository.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void ProductRepository_GetAll_ReturnsMoreThanOneProduct() {

        var product1 = Product
                .builder()
                .title("product1")
                .price(1000)
                .build();

        var product2 = Product
                .builder()
                .title("product1")
                .price(1000)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);

        var productList = productRepository.findAll();

        Assertions.assertThat(productList).isNotNull();
        Assertions.assertThat(productList.size()).isEqualTo(2);
    }

    @Test
    public void ProductRepository_UpdateProduct_ReturnsProductNotNull() {

        var product = Product
                .builder()
                .title("product1")
                .price(1000)
                .build();

        productRepository.save(product);

        var productFromDb = productRepository.findById(product.getId()).orElse(null);
        Assertions.assertThat(productFromDb).isNotNull();

        productFromDb.setTitle("product2");

        var updateProduct = productRepository.save(productFromDb);

        Assertions.assertThat(updateProduct).isNotNull();
        Assertions.assertThat(updateProduct.getTitle()).isNotNull();
    }

    @Test
    public void ProductRepository_DeleteProduct_ReturnsProductIsEmpty() {

        var product = Product
                .builder()
                .title("product1")
                .price(1000)
                .build();

        var productFromDb = productRepository.save(product);

        productRepository.deleteById(productFromDb.getId());
        var productReturn = productRepository.findById(productFromDb.getId());

        Assertions.assertThat(productReturn).isEmpty();
    }
}
