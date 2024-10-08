package com.project.home.controller;

import com.project.home.service.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.project.home.entity.City;
import com.project.home.entity.User;

import javax.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private HomeService homeService;

	@Autowired
	private UserService userService;

	@Autowired
	private CityService cityService; 
	

	@Autowired
	private ProvinceService provinceService; 

	@Autowired
	private CategoryService categoryService; 
	
	@RequestMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user",new User());
		return "pages/login";
	}


	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("user",new User());
		model.addAttribute("loginErr",true);
		return "pages/login";
	}



	@PostMapping("/register")
	public String registerPage(@ModelAttribute @Valid User user ,BindingResult bindingResult ) throws IllegalAccessException, IOException, InvocationTargetException {
		if (bindingResult.hasErrors())
			return "pages/login";
		userService.addUser(user);
		return "redirect:/login";
	}

	@RequestMapping("/")
	public String indexPage(Model model) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		boolean isAuth=authentication.getName()=="anonymousUser"?false:true;
		model.addAttribute("homes",homeService.getAllHome());
		if(isAuth)
			model.addAttribute("user",userService.getUserByUser(authentication.getName()));
		model.addAttribute("isAuth",isAuth);
		model.addAttribute("provinces",provinceService.getAllProvince());
		model.addAttribute("categories",categoryService.getAllCategory());
		return  "pages/index";
	}


	@RequestMapping("/getCities/{pid}")
	public @ResponseBody List<City> getAllCitis(@PathVariable long pid){
		return cityService.getCitiesByProvinceId(pid);
		
	}
	@RequestMapping("/errorPage")
	public String errorPage(){
		return "/pages/errorPage";
	}
	

}
