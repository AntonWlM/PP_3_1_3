package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;
import ru.kata.spring.boot_security.demo.models.User;

import java.security.Principal;

@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    private final UserService userService;


    private final RoleService roleService;
    @Autowired
    public AdminController(UserServiceImp userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", userService.list());
        return "admin/list";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.listRoles());
        return "admin/new_user";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user){
        String encodedPassword = new BCryptPasswordEncoder(12).encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam(value = "id") Long id,
                           Model model) {
        model.addAttribute("roles", roleService.listRoles());
        model.addAttribute("user", userService.find(id));
        return "admin/edit";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/admin/";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }
    @GetMapping(value = "/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        return "user/user";
    }
}


