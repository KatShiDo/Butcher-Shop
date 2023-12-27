package com.mirea.butcher_shop.repo;

import com.mirea.butcher_shop.domain.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
}
