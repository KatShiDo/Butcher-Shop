package com.mirea.butcher_shop.repositories;

import com.mirea.butcher_shop.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, Long> {
}
