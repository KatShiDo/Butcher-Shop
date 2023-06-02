package com.mirea.butcher_shop.repositories;

import com.mirea.butcher_shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
