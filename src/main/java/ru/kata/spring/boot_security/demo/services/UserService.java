package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Service
public interface UserService {

    User findUserByUsername(String username);

    UserDetails loadUserByUsername(String username);
    void save(User user);

    List<User> list();

    User find(Long id);

    void delete(Long id);

}
