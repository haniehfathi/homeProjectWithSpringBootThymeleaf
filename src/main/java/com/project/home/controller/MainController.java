package com.project.home.controller;

import com.project.home.service.CategoryService;
import com.project.home.service.CityService;
import com.project.home.service.HomeService;
import com.project.home.service.ProvinceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.home.entity.City;
import com.project.home.entity.User;

@Controller
public class MainController {

	@Autowired
	private HomeService homeService;

	@Autowired
	private CityService cityService; 
	

	@Autowired
	private ProvinceService provinceService; 

	@Autowired
	private CategoryService categoryService; 
	
	@RequestMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute(new User());
		return "pages/login";
	}

	@RequestMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("homes",homeService.getAllHome());
		model.addAttribute("provinces",provinceService.getAllProvince());
		model.addAttribute("categories",categoryService.getAllCategory());
		return  "pages/index";
	}
	

	@RequestMapping("/register")
	public String registerPage() {
		return "pages/register";
	}
	
	@RequestMapping("/getCities/{pid}")
	public @ResponseBody List<City> getAllCitis(@PathVariable long pid){
		return cityService.getCitiesByProvinceId(pid);
		
	}
	

}
