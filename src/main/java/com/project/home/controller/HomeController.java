package com.project.home.controller;

import com.project.home.entity.Home;
import com.project.home.entity.User;
import com.project.home.service.CategoryService;
import com.project.home.service.HomeService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.project.home.service.ProvinceService;
import com.project.home.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/home/")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private UserService userService;


    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/homeDetails/{id}")
    public String homeDetails(Model model, @PathVariable("id") Long id)
    {
        Home home=homeService.getHomeById(id);
        User usr=userService.getUserById(home.getUser().id);
        model.addAttribute("home",home);
        model.addAttribute("user",usr);

        return "/pages/home/homeDetails";
    }


    @GetMapping("/rest/{id}")
    public @ResponseBody Home getHome(@PathVariable("id") long id)
    {
        return homeService.getHomeById(id);
        
    }

    @RequestMapping("/add")
    public @ResponseBody Home  add(@ModelAttribute Home home) throws IOException, InvocationTargetException, IllegalAccessException {
        return  homeService.addHome(home);
    }


    @GetMapping("/getBySearch")
    public String getHomesBySearch(@ModelAttribute("h")Home home,Model model,@PageableDefault(size = 5)Pageable pageable) {
    	model.addAttribute("homes",homeService.findBySearch(home, pageable));
    	
    	return "/pages/home/homes";
    }


    @GetMapping("/editHome/{id}")
    public String editHome(Model model, @PathVariable("id") long id)
    {
        return "editHome";

    }


    @GetMapping("/addHome")
    public String addHome(Model model) throws IOException
    {

        model.addAttribute("home",new Home());
        model.addAttribute("provinces",provinceService.getAllProvince());
        model.addAttribute("categories",categoryService.getAllCategory());
        return  "/pages/home/addHome";
    }


    @PostMapping("/addHome")
    public String addHome(@ModelAttribute @Valid  Home home, BindingResult bindingResult) throws  IOException, InvocationTargetException, IllegalAccessException
    {
        if(bindingResult.hasErrors())
            return "pages/home/addHome";

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        home.setUser(userService.getUserByUser(username));
        homeService.addHome(home);
        return  "redirect:/";
    }

}
