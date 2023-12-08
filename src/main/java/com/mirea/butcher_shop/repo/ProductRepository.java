package com.mirea.butcher_shop.repo;

import com.mirea.butcher_shop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
