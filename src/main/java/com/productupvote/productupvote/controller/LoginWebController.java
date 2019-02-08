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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.appsdeveloperblog.encryption.PassUtil;

import java.util.Date;

/**
 * LoginWebController
 * This class controls requests for:
 * login, logout, register and delete user.
 * @author U1554969 Jakub Chruslicki
 */
@Controller
public class LoginWebController extends AppController{

    @Autowired
    UserService userService;

    /**
     * This method returns register form.
     * @param model supply attributes used for rendering views.
     * @param user empty user object ready to store data.
     * @return directory path of the html page to render.
     */
    @GetMapping("/register")
    public String displayRegisterForm(Model model, User user){
        model.addAttribute(super.PAGE_TITLE_ID = "Register");
        model.addAttribute(super.USER, user);
        return "frontend/register/register";
    }

    /**
     * This method gets user object through POST, validates it and saves it in database.
     * @param model supply attributes used for rendering views.
     * @param user user object which stores user input.
     * @param bindingResult result of the object binding with form input.
     * @return directory path of the html page to render.
     */
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

    /**
     * This method returns login form.
     * @param model supply attributes used for rendering views.
     * @param user user object which stores user input.
     * @return directory path of the html page to render.
     */
    @GetMapping("/login")
    public String displayLoginForm(Model model, User user){
        model.addAttribute(super.PAGE_TITLE_ID = "Login");
        model.addAttribute(super.USER, user);
        return "frontend/login/login";
    }

    /**
     * This method gets user object with data and checks the database if this user already exists.
     * If user exists verifies the password and creates new user session.
     * @param model supply attributes used for rendering views.
     * @param user user object which stores user input.
     * @param request request information for HTTP Servlets.
     * @return directory path of the html page to render.
     */
    @PostMapping("/login")
    public String displayLoginForm(Model model, @Valid @ModelAttribute("user") User user, HttpServletRequest request){
        User tempUser = userService.findUserByEmail(user.getEmail());
        if(tempUser !=null) {
            if(PassUtil.verifyUserPassword(user.getPassword(),tempUser.getPassword(),tempUser.getSalt())) {
                model.addAttribute("message", "Success");
                request.getSession().setAttribute("user", tempUser);
                return super.HOMEPAGE_REDIRECT;
            }else{
                model.addAttribute("message", "Password did not match.");
                System.out.println("Password result error");
                return this.displayLoginForm(model, user);
            }
        }
        else{
            model.addAttribute("message", "Can't find user with this email address.");
            System.out.println("Email result error");
            return this.displayLoginForm(model, user);
        }

    }

}
