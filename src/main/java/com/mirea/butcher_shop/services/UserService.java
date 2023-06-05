package com.mirea.butcher_shop.services;

import com.mirea.butcher_shop.models.User;
import com.mirea.butcher_shop.models.enums.Role;
import com.mirea.butcher_shop.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean create(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with Email: {}", email);
        userRepository.save(user);
        return true;
    }

    public void update(User user) {
        String email = user.getEmail();
        User userFromDb = userRepository.findByEmail(email);
        userFromDb.setPassword(user.getPassword());
        userFromDb.setName(user.getName());
        userFromDb.setPhoneNumber(user.getPhoneNumber());
        userFromDb.setProducts(user.getProducts());
        userFromDb.setActive(user.isActive());
        userFromDb.setRoles(user.getRoles());
        userRepository.save(userFromDb);
    }

    public void ban(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("User with Email: {} was banned", user.getEmail());
            }
            else {
                user.setActive(true);
                log.info("User with Email: {} was unbanned", user.getEmail());
            }
            userRepository.save(user);
        }
    }

    public User getByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    public void changeRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
}
