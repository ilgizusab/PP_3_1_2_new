package ru.kata.pp_3_1_2_new.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.pp_3_1_2_new.model.User;
import ru.kata.pp_3_1_2_new.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/user/profile")
    public String updateProfile(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/user/profile";
    }
}