package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.dao.UserDao;
import com.example.springboot.model.User;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController{

    private final UserDao userDao;

    @Autowired
    public AdminController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public List<User> usersList(ModelMap model, Principal principal) {
//        User user = userDao.getUserByName(principal.getName());
//        String msg = user.getName() + " with role: " + user.getRoles();
//        model.addAttribute("msg", msg);
//        model.addAttribute("users", userDao.getAllUsers());
//        model.addAttribute("user", user);
        return userDao.getAllUsers();
    }

//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String loginPage() {
//        return "login";
//    }

//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "new";
//    }

    @PostMapping
    public User create(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getRoles());
        userDao.addUser(user);
        return user;
    }

//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userDao.getUserById(id));
//        return "edit";
//    }

//    @GetMapping("/currentUser")
//    @ResponseBody
//    public User currentUserInfo(@RequestParam(value = "id") Long id) {
//        return userDao.getUserById(id);
//    }

    @PatchMapping("/edit")
    public User update(@RequestBody User user) {
        userDao.updateUser(user);
        return user;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") long id) {
        userDao.deleteUser(id);
        return "Success";
    }
}
