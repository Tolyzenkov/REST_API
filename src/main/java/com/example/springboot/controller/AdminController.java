package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.dao.UserDao;
import com.example.springboot.model.User;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public String usersList(ModelMap model) {
        model.addAttribute("users", userDao.getAllUsers());
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(required = false, name = "role") List<String> role) {
        System.out.println(user.getName());
        System.out.println(role);
        userDao.addUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(Model model,
                         @ModelAttribute("user") User user,
                         @PathVariable("id") long id,
                         @RequestParam(required = false, name = "role") List<String> role) {
        userDao.updateUser(user, id, role);
        return "redirect:/admin";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") long id) {
        userDao.deleteUser(id);
        return "redirect:/admin";
    }
}
