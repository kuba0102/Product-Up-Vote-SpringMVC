package com.productupvote.productupvote.controller;

import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import com.appsdeveloperblog.encryption.PassUtil;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * LoginWebController
 * This class controls requests for:
 * login, logout, register and delete user.
 * @author U1554969 Jakub Chruslicki @version 0.1
 */
@Controller
public class LoginWebController extends AppController{

    @Autowired
    UserService userService;
    @GetMapping("/register")
    public String displayRegisterForm(Model model, User user){
        model.addAttribute(super.PAGE_TITLE_ID = "Register");
        model.addAttribute(super.USER, user);
        return "frontend/register/register";
    }

    @PostMapping("/register")
    public String registerForm(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(!bindingResult.hasErrors() && user.getPassword().equals(user.getSalt())) {
            user.setSalt(PassUtil.getSalt(30));
            user.setPassword(PassUtil.generateSecurePassword(user.getPassword(), user.getSalt()));
            user.setDateCreated(new Date());
            userService.save(user);
            System.out.println(user.toString());
        }else{
            model.addAttribute("error", "Password did not match.");
            System.out.println("Binding result error");
            return this.displayRegisterForm(model, user);
        }
        return super.LOGIN_REDIRECT;
    }

}
