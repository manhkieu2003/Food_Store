package com.example.food.controller;

import com.example.food.entity.Cart;
import com.example.food.entity.User;
import com.example.food.loginCredentials.UserLogin;
import com.example.food.service.CartService;
import com.example.food.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @PostMapping("/addingUser")
    public String addUser(@ModelAttribute("user") User user){
     User users  =userService.addUser(user);

        return "redirect:/add_user";
    }
    @PostMapping("/updatingUser/{idUser}")
    public String updatingUser(@ModelAttribute("user") User user , @PathVariable("idUser") Integer id){
       this.userService.updateUser(user,id);
       return "redirect:/admin/services";
    }
    @GetMapping("/deleteUser/{idUser}")
    public String deleteUser( @PathVariable("idUser") Integer id){
        this.userService.deleteUser(id);
        return "redirect:/admin/services";
    }


}
