package com.example.springboot.controller;


import com.example.springboot.dao.UserDao;
import com.example.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestAdminController {


    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RestAdminController(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @GetMapping("/admin/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userDao.getUserById(id);
    }

    @PostMapping("/admin")
    public List<User> create(@RequestBody User user) {
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        userDao.addUser(user);
        return userDao.getAllUsers();
    }

    @PatchMapping("/admin/edit")
    public List<User> update(@RequestBody User user) {
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        userDao.updateUser(user);
        System.out.println(user.toString());
        return userDao.getAllUsers();
    }

    @DeleteMapping("/admin/delete/{id}")
    public List<User> delete(@PathVariable("id") long id) {
        userDao.deleteUser(id);
        return userDao.getAllUsers();
    }

}
