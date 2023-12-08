package com.mirea.butcher_shop.repo;

import com.mirea.butcher_shop.domain.entities.User;
import com.mirea.butcher_shop.domain.enums.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_Save_ReturnsSavedUser() {

        var roles = new HashSet<Role>();
        roles.add(Role.ROLE_ADMIN);

        var user = User
                .builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("1234")
                .roles(roles)
                .build();

        var savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_GetAll_ReturnsMoreThanOneUser() {

        var roles = new HashSet<Role>();
        roles.add(Role.ROLE_ADMIN);

        var user1 = User
                .builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("1234")
                .roles(roles)
                .build();

        var user2 = User
                .builder()
                .username("user2")
                .email("user2@gmail.com")
                .password("1234")
                .roles(roles)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        var userList = userRepository.findAll();

        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    public void UserRepository_FindByUsername_ReturnsUserNotNull() {

        var roles = new HashSet<Role>();
        roles.add(Role.ROLE_ADMIN);

        var user = User
                .builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("1234")
                .roles(roles)
                .build();

        userRepository.save(user);

        var userFromDb = userRepository.findByUsername(user.getUsername()).orElse(null);

        Assertions.assertThat(userFromDb).isNotNull();
    }

    @Test
    public void UserRepository_UpdateUser_ReturnsUserNotNull() {

        var roles = new HashSet<Role>();
        roles.add(Role.ROLE_ADMIN);

        var user = User
                .builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("1234")
                .roles(roles)
                .build();

        userRepository.save(user);

        var userFromDb = userRepository.findById(user.getId()).orElse(null);
        Assertions.assertThat(userFromDb).isNotNull();

        userFromDb.setUsername("user2");

        var updatedUser = userRepository.save(userFromDb);

        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getUsername()).isNotNull();
    }

    @Test
    public void UserRepository_DeleteUser_ReturnsUserIsEmpty() {

        var roles = new HashSet<Role>();
        roles.add(Role.ROLE_ADMIN);

        var user = User
                .builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("1234")
                .roles(roles)
                .build();

        var userFromDb = userRepository.save(user);

        userRepository.deleteById(userFromDb.getId());
        var userReturn = userRepository.findById(userFromDb.getId());

        Assertions.assertThat(userReturn).isEmpty();
    }
}
