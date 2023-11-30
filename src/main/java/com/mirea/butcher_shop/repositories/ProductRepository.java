package com.mirea.butcher_shop.repositories;

import com.mirea.butcher_shop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
