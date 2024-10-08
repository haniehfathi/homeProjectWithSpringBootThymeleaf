package com.project.home.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.project.home.entity.Home;
import com.project.home.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.home.entity.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private HomeService homeService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/addUser")
	public String registerUser(@ModelAttribute User user) throws IOException, InvocationTargetException, IllegalAccessException {
		userService.addUser(user);
		return "/pages/user/userInfo";
	}
	@GetMapping("/profile/userInfo")
	public String userInfo(Model model, @PageableDefault(size = 2)Pageable pageable) throws IOException, InvocationTargetException, IllegalAccessException {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		User authUser=userService.getUserByUser(username);
		model.addAttribute("user",authUser);
		model.addAttribute("homes",homeService.findByUserId(authUser,pageable));
		return "/pages/user/userInfo";
	}
	@RequestMapping("/changePassword")
	public String changePassword(@ModelAttribute User user) throws IOException, InvocationTargetException, IllegalAccessException {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			User authUser=userService.getUserByUser(username);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches( user.getOldPassword(),authUser.getPassword()))
			{
				user.setPassword(user.getNewPassword());
				userService.changePassword(user);
			}

			return "/pages/user/userInfo";
		}
		catch (Exception e)
		{
			System.out.printf("Error Change Password : "+e.toString());
			return "redirect:/";
		}

	}

	@GetMapping("/home/edit/{id}")
	public String editHome(Model model, @PathVariable("id") Long id)
	{
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		User authUser=userService.getUserByUser(username);
		model.addAttribute("user",authUser);

		model.addAttribute("home",homeService.getHomeById(id));
		model.addAttribute("provinces",provinceService.getAllProvince());
		model.addAttribute("categories",categoryService.getAllCategory());
		return  "/pages/user/editHome";
	}

	@GetMapping("/profile/allHome")
	public String editHome(Model model, @PageableDefault(3) Pageable pageable)
	{
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		User authUser=userService.getUserByUser(username);
		model.addAttribute("user",authUser);
		model.addAttribute("homes",homeService.findByUserId(authUser,pageable));
		return "/pages/user/allUserHomes";
	}

	@PostMapping("/home/edit")
	public String editHome(@ModelAttribute Home home) throws IllegalAccessException, IOException, InvocationTargetException {
		homeService.addHome(home);
		return "redirect:/user/profile/allHome";
	}
}
