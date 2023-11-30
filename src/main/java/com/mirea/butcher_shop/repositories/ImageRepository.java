package com.mirea.butcher_shop.repositories;

import com.mirea.butcher_shop.domain.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
