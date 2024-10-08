package com.project.home.controller;

import com.project.home.entity.Category;
import com.project.home.entity.Home;
import com.project.home.entity.User;
import com.project.home.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private ProvinceService provinceService;

 @Autowired
    private HomePicturesService homePicturesService;



    @GetMapping("/category/allCategories")
    public String allCategory(@ModelAttribute("c") Category category, Model model )
    {
        model.addAttribute("categories",categoryService.findBySearch(category));
        return  "/pages/admin/allCategories";
    }

    @GetMapping("/category/addCategory")
    public String addCategory(Model model)
    {
        model.addAttribute("category",new Category());
        return  "/pages/admin/addCategory";
    }

    @PostMapping("/category/addCategory")
    public String addCategory(@ModelAttribute @Valid Category category, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "/pages/admin/addCategory";
        categoryService.addCategory(category);
        return  "redirect:/admin/category/allCategories";
    }

    @GetMapping("/category/edit/{id}")
    public String editCategory(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("category",categoryService.findCategoryById(id));
        return  "/pages/admin/addCategory";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id)
    {
        categoryService.deleteCategory(id);
        return  "redirect:/admin/category/allCategories";
    }




    @GetMapping("/user/allUsers")
    public String allUsers(@ModelAttribute("u") User user, Model model, @PageableDefault(10)Pageable pageable)
    {

        model.addAttribute("users",userService.getAllUsersBySearch(user,pageable));
        return  "/pages/admin/allUsers";
    }

    @GetMapping("/user/addUser")
    public String addUser(Model model)
    {
        model.addAttribute("user",new User());
        return  "/pages/admin/addUser";
    }

    @PostMapping("/user/addUser")
    public String addUser(@ModelAttribute @Valid User user,BindingResult bindingResult) throws IOException, InvocationTargetException, IllegalAccessException {
        if(bindingResult.hasErrors())
            return   "/pages/admin/addUser";
        userService.addUser(user);
        return  "redirect:/admin/user/allUsers";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("user",userService.getUserById(id));
        return  "/pages/admin/addUser";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=userService.getUserById(id);
        if(user.getEmail()==authentication.getName())
            return  "redirect:/admin/user/allUsers";
        userService.deleteUser(user);
        return  "redirect:/admin/user/allUsers";
    }



    @GetMapping("/home/allHomes")
    public String allHomes(@ModelAttribute("h") Home home, Model model, @PageableDefault(10)Pageable pageable)
    {
        model.addAttribute("homes",homeService.findBySearch(home,pageable));
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("provinces",provinceService.getAllProvince());
        return  "/pages/admin/allHomes";
    }

    @GetMapping("/home/addHome")
    public String addHome(Model model)
    {
        model.addAttribute("home",new Home());
        model.addAttribute("provinces",provinceService.getAllProvince());
        model.addAttribute("categories",categoryService.getAllCategory());
        return  "/pages/admin/addHome";
    }

    @PostMapping("/home/addHome")
    public String addHome(@ModelAttribute @Valid Home home,BindingResult bindingResult) throws IOException, InvocationTargetException, IllegalAccessException {
        if (bindingResult.hasErrors())
            return  "/pages/admin/addHome";
        homeService.addHome(home);
        return  "redirect:/admin/home/allHomes";
    }

    @GetMapping("/home/edit/{id}")
    public String editHome(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("home",homeService.getHomeById(id));
        model.addAttribute("provinces",provinceService.getAllProvince());
        model.addAttribute("categories",categoryService.getAllCategory());
        return  "/pages/admin/addHome";
    }

    @GetMapping("/home/delete/{id}")
    public String deleteHome(@PathVariable("id") Long id)
    {
        Home home=homeService.getHomeById(id);
        homePicturesService.deleteByHomeId(id);
       homeService.deleteHomeById(id);
        return  "redirect:/admin/home/allHomes";
    }


    @RequestMapping("/home/enable/{id}")
    public String enableHome(@PathVariable("id") Long id)
    {
        homeService.enableHome(homeService.getHomeById(id));
        return  "redirect:/admin/home/enableHomes";
    }



    @RequestMapping("/home/disable/{id}")
    public String disableHome(@PathVariable("id") Long id)
    {
        homeService.disableHome(homeService.getHomeById(id));
        return  "redirect:/admin/home/allHomes";
    }



    @GetMapping("/home/enableHomes")
    public String enableHome(Model model,@PageableDefault(size = 10) Pageable pageable)
    {
       model.addAttribute("homes",homeService.findDisabledHome(pageable));
        return  "/pages/admin/enableHome";
    }



}
