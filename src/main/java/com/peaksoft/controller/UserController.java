package com.peaksoft.controller;

import com.peaksoft.model.User;
import com.peaksoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/add-user")
    public String getUser(User user,Model model) {
        model.addAttribute("users", user);
        return "add-user";
    }


    @PostMapping("/save-user")
    public String addUser(@ModelAttribute("users") User user, Model model) {
        userService.addUser(user);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit-user")
    public String UpdateUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@ModelAttribute User user, Model model) {
        userService.updateUser(user);
        model.addAttribute("users",user);
        return "redirect:/users";
    }


    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id,Model model) {
       User user = userService.getById(id);
        userService.removeUser(user);
    return "redirect:/users";
}

    }
