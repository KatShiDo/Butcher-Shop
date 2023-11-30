package com.mirea.butcher_shop.repositories;

import com.mirea.butcher_shop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);
}
