package com.productupvote.productupvote.controller.frontend;

import com.appsdeveloperblog.encryption.PassUtil;
import com.productupvote.productupvote.controller.AppController;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

/**
 * FrontLoginWebController
 * This class controls requests for:
 * displayRegisterForm, registerForm, displayLoginForm, loginForm, logoutUser and updateUserDetails.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Controller
public class FrontLoginWebController extends AppController {

    @Autowired
    UserService userService;

    @Autowired
    FrontProductWebController frontProductWebController;

    @Autowired
    PermissionService permissionService;

    /**
     * This method returns register forms.
     *
     * @param model supply attributes used for rendering views.
     * @param user  empty user object ready to store data.
     * @return directory path of the html page to render.
     */
    @GetMapping("/register")
    public String displayRegisterForm(Model model, User user) {
        model.addAttribute(super.PAGE_TITLE_ID, "Register");
        model.addAttribute(super.USER, user);
        return "frontend/register/register";
    }

    /**
     * This method gets user object through POST, validates it and saves it in database.
     *
     * @param model         supply attributes used for rendering views.
     * @param user          user object which stores user input.
     * @param bindingResult result of the object binding with forms input.
     * @return directory path of the html page to render.
     */
    @PostMapping("/register")
    public String registerForm(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(userService.findUserByEmail(user.getEmail()) == null) {
            if (!bindingResult.hasErrors() && user.getPassword().equals(user.getSalt())) {
                user.setSalt(PassUtil.getSalt(30));
                user.setPassword(PassUtil.generateSecurePassword(user.getPassword(), user.getSalt()));
                user.setDateCreated(new Date());
                user.setBackend(false);
                userService.save(user);
                System.out.println(user.toString());
            } else {
                model.addAttribute("error", "Password did not match.");
                System.out.println("FrontLoginWebController - Binding result error");
                return this.displayRegisterForm(model, user);
            }
        }else{
            model.addAttribute("error", "Email already exists.");
            return this.displayRegisterForm(model, user);
        }
        return super.LOGIN_REDIRECT;
    }

    /**
     * This method returns login forms.
     *
     * @param model supply attributes used for rendering views.
     * @param user  user object which stores user input.
     * @return directory path of the html page to render.
     */
    @GetMapping("/login")
    public String displayLoginForm(Model model, User user) {
        model.addAttribute(super.PAGE_TITLE_ID, "Login To ProductUpVote");
        model.addAttribute(super.USER, user);
        model.addAttribute("url", "/login");
        return "login/login";
    }

    /**
     * This method gets user object with data and checks the database if this user already exists.
     * If user exists verifies the password and creates new user session.
     *
     * @param model supply attributes used for rendering views.
     * @param user  user object which stores user input.
     * @return directory path of the html page to render.
     */
    @PostMapping("/login")
    public String loginForm(Model model, @ModelAttribute("user") User user) {
        try {
            System.out.println("Starting Login");
            User tempUser = userService.findUserByEmail(user.getEmail());
            System.out.println("FrontLoginWebController - User found" + tempUser.getEmail());
            if (tempUser != null) {
                if (PassUtil.verifyUserPassword(user.getPassword(), tempUser.getPassword(), tempUser.getSalt())) {
                    tempUser = userService.addVoteDailyLogin(tempUser); // Add votes if correct time has passed.
                    tempUser.setDateOnline(new Date());
                    userService.save(tempUser);
                    userService.setUserSession(tempUser);
                    model.addAttribute("message", "Success");
                    System.out.println("FrontLoginWebController - Message: Success Login");
                    return super.HOMEPAGE_REDIRECT;
                } else {
                    model.addAttribute("message", "Password did not match.");
                    System.out.println("FrontLoginWebController - Password result error");
                    return this.displayLoginForm(model, user);
                }
            }
        } catch (Exception e) {
            model.addAttribute("message", "Can't find user with this email address.");
            System.out.println("FrontLoginWebController - Email result error");
            return this.displayLoginForm(model, user);
        }
        return super.LOGIN_REDIRECT;
    }

    /**
     * This method logs current user out.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/logout")
    public String logoutUser(Model model) {
        userService.cleanSession();
        return displayLoginForm(model, new User());
    }


    /**
     * This method updates users information.
     *
     * @param model    supply attributes used for rendering views.
     * @param user     user to update.
     * @param confPass password confirmation.
     * @return directory path of the html page to render.
     */
    @PostMapping("/update-user")
    public String updateUserDetails(Model model, @ModelAttribute("user") User user,
                                    @RequestParam("confPass") String confPass) {
        User updatedUser = userService.findUserById(user.getId());
        if (!userService.checkLogin(false)) return super.LOGIN_REDIRECT;
        if (userService.getCurrentUser().getId() != user.getId()) {
            model.addAttribute("formMsg", "Can't update this user");
            return frontProductWebController.displayProducts(model);
        }
        if (user.getPassword().equals(confPass)) {
            if (!PassUtil.verifyUserPassword(user.getPassword(), updatedUser.getPassword(), updatedUser.getSalt())) {
                user.setSalt(PassUtil.getSalt(30));
                user.setPassword(PassUtil.generateSecurePassword(user.getPassword(), user.getSalt()));
            }
        } else {
            model.addAttribute("formMsg", "Password did not match");
        }
        user.setDateUpdated(new Date());
        userService.save(user);
        userService.setUserSession(userService.findUserById(user.getId()));
        model.addAttribute("formMsg", "User has been updated");
        return frontProductWebController.displayProducts(model);
    }
}
