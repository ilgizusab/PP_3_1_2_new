package ru.kata.pp_3_1_2_new.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.pp_3_1_2_new.model.User;
import ru.kata.pp_3_1_2_new.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin/editUser";
    }

    @PostMapping("/admin/users/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}