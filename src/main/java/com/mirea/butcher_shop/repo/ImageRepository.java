package com.mirea.butcher_shop.repo;

import com.mirea.butcher_shop.domain.entities.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
}
