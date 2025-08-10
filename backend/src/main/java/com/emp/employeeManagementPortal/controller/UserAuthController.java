package com.emp.employeeManagementPortal.controller;

import com.emp.employeeManagementPortal.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserAuthController {
    @Autowired
    UserAuthService userAuthService;

    @GetMapping("/register")
    public String registerGet(){
        return "forward:/Register.html";
    }

    @PostMapping("/register")
    public String registerPost(@RequestParam int userId,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam int projectId){
        try{
            userAuthService.registerUser(userId, email, password, projectId);
            return "redirect:/login?registered";
        } catch (RuntimeException e) {
            return "redirect:/register?error="+e.getMessage();
        }
    }
}
