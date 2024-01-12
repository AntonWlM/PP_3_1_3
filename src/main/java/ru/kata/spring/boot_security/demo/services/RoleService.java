package ru.kata.spring.boot_security.demo.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositoties.RoleRepository;

import java.util.List;
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Query("select u from User u left join fetch u.roles where u.name=:name")
    public List<Role> listRoles() {return roleRepository.findAll();}
}
