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

@Controller
@RequestMapping("/")
public class AdminController{

    private final UserDao userDao;

    @Autowired
    public AdminController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/admin")
    public String usersList(ModelMap model, Principal principal) {
        User user = userDao.getUserByName(principal.getName());
        String msg = user.getName() + " with role: " + user.getRoles();
        model.addAttribute("msg", msg);
        model.addAttribute("users", userDao.getAllUsers());
        model.addAttribute("user", user);
        return "index";
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


}
