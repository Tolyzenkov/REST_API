package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.springboot.dao.UserDao;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserDao userDao;

	@Autowired
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping()
	public String showUserPage(ModelMap model, Principal principal) {
		User user = userDao.getUserByName(principal.getName());
		Set<Role> roles = user.getRoles();
		String msg = user.getName() + " with role: " + user.getRoles();
		model.addAttribute("msg", msg);
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		model.addAttribute("role", new Role());
		return "userDetails";
	}

}