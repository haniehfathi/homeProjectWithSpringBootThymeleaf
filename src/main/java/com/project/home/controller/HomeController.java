package com.project.home.controller;

import com.project.home.entity.Home;
import com.project.home.service.HomeService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/home/")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/homeDetails/{id}")
    public String homeDetails(Model model, @PathVariable("id") long id)
    {
        model.addAttribute("home",homeService.getHome(id));
        return "/pages/home/homeDetails";
    }


    @GetMapping("/rest/{id}")
    public @ResponseBody Home getHome(@PathVariable("id") long id)
    {
        return homeService.getHome(id);
        
    }

    @RequestMapping("/addHome")
    public @ResponseBody Home  addHome(@ModelAttribute Home home) throws IOException
    {
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


}
