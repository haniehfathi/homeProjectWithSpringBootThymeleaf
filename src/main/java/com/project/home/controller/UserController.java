package com.project.home.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.home.entity.User;
import com.project.home.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	public String registerUser(@ModelAttribute User user) throws IOException
	{
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.addUser(user);
		return "/pages/register";
		
	}

}
