package com.mirea.butcher_shop.repositories;

import com.mirea.butcher_shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
