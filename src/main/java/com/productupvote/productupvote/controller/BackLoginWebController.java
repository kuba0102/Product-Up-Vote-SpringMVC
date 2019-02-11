package com.productupvote.productupvote.controller;

import com.appsdeveloperblog.encryption.PassUtil;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * BackLoginWebController
 * This class controls requests for:
 *
 * @author U1554969 Jakub Chruslicki
 */
@RequestMapping("/backend")
@Controller
public class BackLoginWebController extends AppController {

    @Autowired
    UserService userService;

    /**
     * This method returns register form.
     *
     * @param model supply attributes used for rendering views.
     * @param user  empty user object ready to store data.
     * @return directory path of the html page to render.
     */
    @PreAuthorize("userService.checkLogin()")
    @GetMapping("/add-user")
    public String displayAddUserForm(Model model, User user) {
        if (!userService.checkLogin()) return super.BACKEND_LOGIN_REDIRECT;
        model.addAttribute(super.PAGE_TITLE_ID, "Add New User");
        model.addAttribute(super.USER, user);
        return "backend/add-user/add-user-form";


    }

    /**
     * This method gets user object through POST, validates it and saves it in database.
     *
     * @param model         supply attributes used for rendering views.
     * @param user          user object which stores user input.
     * @param bindingResult result of the object binding with form input.
     * @return directory path of the html page to render.
     */
    @PostMapping("/add-user")
    public String addNewUserForm(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && user.getPassword().equals(user.getSalt())) {
            user.setSalt(PassUtil.getSalt(30));
            user.setPassword(PassUtil.generateSecurePassword(user.getPassword(), user.getSalt()));
            user.setDateCreated(new Date());
            user.setBackend(true);
            userService.save(user);
            System.out.println(user.toString());
        } else {
            model.addAttribute("error", "Password did not match.");
            System.out.println("Binding result error");
            return this.displayAddUserForm(model, user);
        }
        return displayAddUserForm(model, new User());
    }

    /**
     * This method returns login form.
     *
     * @param model supply attributes used for rendering views.
     * @param user  user object which stores user input.
     * @return directory path of the html page to render.
     */
    @GetMapping("/login")
    public String displayBackendLoginForm(Model model, User user) {
        if (userService.checkLogin()) return super.BACKEND_HOMEPAGE_REDIRECT;
        model.addAttribute(super.PAGE_TITLE_ID, "Backend Login");
        model.addAttribute(super.USER, user);
        return "login/login";
    }

    /**
     * This method gets user object with data and checks the database if this user already exists.
     * If user exists verifies the password and creates new user session.
     *
     * @param model   supply attributes used for rendering views.
     * @param user    user object which stores user input.
     * @param request request information for HTTP Servlets.
     * @return directory path of the html page to render.
     */
    @PostMapping("/login")
    public String loginForm(Model model, @ModelAttribute("user") User user, HttpServletRequest request) {
        if (userService.checkLogin()) return super.BACKEND_HOMEPAGE_REDIRECT;
        try {
            System.out.println("Starting Login");
            User tempUser = userService.findUserByEmail(user.getEmail());
            System.out.println("User found" + tempUser.getEmail());
            if (tempUser != null) {
                if (PassUtil.verifyUserPassword(user.getPassword(), tempUser.getPassword(), tempUser.getSalt()) && tempUser.isBackend()) {
                    model.addAttribute("message", "Success");
                    tempUser.setDateOnline(new Date());
                    userService.save(tempUser);
                    request.getSession().setAttribute("user", tempUser);
                    System.out.println("Message: Success Login");
                    return BACKEND_HOMEPAGE_REDIRECT;
                } else {
                    model.addAttribute("message", "Password did not match.");
                    System.out.println("Password result error");
                    return this.displayBackendLoginForm(model, user);
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", "Can't find user with this email address.");
            System.out.println("Email result error");
            return this.displayBackendLoginForm(model, user);
        }
        return super.BACKEND_LOGIN_REDIRECT;
    }

    @GetMapping("/")
    public String displayHome(Model model, User user) {
        if (!userService.checkLogin()) return super.BACKEND_LOGIN_REDIRECT;
        model.addAttribute(super.PAGE_TITLE_ID, "Home");
        model.addAttribute(super.USER, user);
        return "backend/index/index-backend";
    }

}
