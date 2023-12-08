package com.mirea.butcher_shop.services;

import com.mirea.butcher_shop.domain.entities.User;
import com.mirea.butcher_shop.domain.enums.Role;
import com.mirea.butcher_shop.repo.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void UserService_CreateUser_ReturnsBoolean() {

        var roles = new HashSet<Role>();
        roles.add(Role.ROLE_ADMIN);

        var user = User
                .builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("1234")
                .roles(roles)
                .build();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        var result = userService.create(user);

        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void UserService_GetAll_ReturnsUsers() {

        var roles = new HashSet<Role>();
        roles.add(Role.ROLE_ADMIN);

        var user = User
                .builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("1234")
                .roles(roles)
                .build();

        var users = new ArrayList<User>();
        users.add(user);

        when(userRepository.findAll()).thenReturn(users);

        var result = userService.getAll();

        Assertions.assertThat(result).isNotNull();
    }
}
