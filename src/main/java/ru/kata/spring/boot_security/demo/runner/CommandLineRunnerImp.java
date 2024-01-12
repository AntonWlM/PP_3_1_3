package ru.kata.spring.boot_security.demo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerImp implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    public CommandLineRunnerImp(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        if (roleService.findRoleByName("ROLE_ADMIN") == null) {

            roleService.saveRole(new Role("ROLE_ADMIN"));
        }

        if (roleService.findRoleByName("ROLE_USER") == null) {
            roleService.saveRole(new Role("ROLE_USER"));
        }

        User admin = userService.findUserByUsername("admin");
        if (admin == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findRoleByName("ROLE_ADMIN"));
            admin = new User(
                    35,
                    "admin",
                    "admin",
                    "$2a$12$im/KwQaC6Ia.Dj9BiFYmxeSh1ClnDt8YAGnHYqieTa.8Jn7dhp4Kq", //admin
                    "admin",
                    roles
            );
            userService.saveUser(admin);
        }

        User user = userService.findUserByUsername("user");
        if (user == null) {
            Set<Role> roles2 = new HashSet<>();
            roles2.add(roleService.findRoleByName("ROLE_USER"));
            user = new User(
                    30,
                    "user",
                    "user",
                    "$2a$12$QKGsxf11dkQ9jthWA9S64ufdCHRGfeL0gINxSEYmAnujjoJH.DAPG", //user
                    "user",
                    roles2);
            userService.saveUser(user);
        }
    }
}

