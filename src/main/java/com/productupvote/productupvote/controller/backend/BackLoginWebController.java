package com.productupvote.productupvote.controller.backend;

import com.appsdeveloperblog.encryption.PassUtil;
import com.productupvote.productupvote.controller.AppController;
import com.productupvote.productupvote.domain.Permission;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.domain.UserType;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.UserService;
import com.productupvote.productupvote.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;

/**
 * BackLoginWebController
 * This class controls requests for:
 * displayAddUserForm, addNewUserForm, displayBackendLoginForm, loginForm, displayHome, logoutUser and addBasicUser.
 *
 * @author U1554969 Jakub Chruslicki
 */
@RequestMapping("/backend")
@Controller
public class BackLoginWebController extends AppController {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserTypeService userTypeService;

    /**
     * This method returns register forms.
     *
     * @param model supply attributes used for rendering views.
     * @param user  empty user object ready to store data.
     * @return directory path of the html page to render.
     */
    @GetMapping("/add-user")
    public String displayAddUserForm(Model model, User user) {
        if (!userService.checkLogin(true)) return super.BACKEND_LOGIN_REDIRECT;
        if(!permissionService.getCurrentUserPermission().isUserAdd()) return super.displayUnauthorised(model, "null","Unauthorised to add user");
        model.addAttribute(super.DIRECTORY, "backend/user/add-user-form");
        model.addAttribute(super.PAGE_TITLE_ID, "Add New User");
        model.addAttribute("newUser", user);
        model.addAttribute(super.USER, userService.getCurrentUser());
        return "backend/index/index-backend";
    }

    /**
     * This method gets user object through POST, validates it and saves it in database.
     *
     * @param model         supply attributes used for rendering views.
     * @param user          user object which stores user input.
     * @param bindingResult result of the object binding with forms input.
     * @return directory path of the html page to render.
     */
    @PostMapping("/add-user")
    public String addNewUserForm(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(!permissionService.getCurrentUserPermission().isUserAdd()) return super.displayUnauthorised(model, "ajax","Unauthorised to add user");
        if (!bindingResult.hasErrors() && user.getPassword().equals(user.getSalt())) {
            user.setSalt(PassUtil.getSalt(30));
            user.setPassword(PassUtil.generateSecurePassword(user.getPassword(), user.getSalt()));
            this.addBasicUser(user);
            System.out.println(user.toString());
        } else {
            model.addAttribute("error", "Password did not match.");
            System.out.println("BackLoginWebController - Negative Result : Binding result error");
            return this.displayAddUserForm(model, user);
        }
        return displayAddUserForm(model, new User());
    }

    /**
     * This method returns login forms.
     *
     * @param model supply attributes used for rendering views.
     * @param user  user object which stores user input.
     * @return directory path of the html page to render.
     */
    @GetMapping("/login")
    public String displayBackendLoginForm(Model model, User user) {
        if (userService.checkLogin(true)) return super.BACKEND_HOMEPAGE_REDIRECT;
        model.addAttribute(super.PAGE_TITLE_ID, "Backend Login");
        model.addAttribute(super.USER, user);
        model.addAttribute("url", "/backend/login");
        return "login/login";
    }

    /**
     * This method gets user object with data and checks the database if this user already exists.
     * If user exists verifies the password and creates new user session.
     *
     * @param model   supply attributes used for rendering views.
     * @param user    user object which stores user input.
     * @return directory path of the html page to render.
     */
    @PostMapping("/login")
    public String loginForm(Model model, @ModelAttribute("user") User user) {
        if (userService.checkLogin(true)) return super.BACKEND_HOMEPAGE_REDIRECT;
        try {
            System.out.println("Positive Result: Starting Login");
            User tempUser = userService.findUserByEmail(user.getEmail());
            System.out.println("BackLoginWebController - Positive Result: User found: " + tempUser.getEmail());
            if (tempUser != null) {
                if(!tempUser.isBackend()){
                    model.addAttribute("message", "No backend access granted.");
                    System.out.println("Permissions error");
                    return this.displayBackendLoginForm(model, user);
                }
                if (PassUtil.verifyUserPassword(user.getPassword(), tempUser.getPassword(), tempUser.getSalt()) && tempUser.isBackend()) {
                    model.addAttribute("message", "Success Login");
                    tempUser.setDateOnline(new Date());
                    userService.save(tempUser);
                    userService.setUserSession(tempUser);
                    System.out.println("BackLoginWebController - Message: Success Login");
                    return BACKEND_HOMEPAGE_REDIRECT;
                } else {
                    model.addAttribute("message", "Password did not match.");
                    System.out.println("BackLoginWebController - Password result error");
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

    /**
     * This method displays backend home page.
     *
     * @param model supply attributes used for rendering views.
     * @param user  current user.
     * @return directory path of the html page to render.
     */
    @GetMapping("/")
    public String displayHome(Model model, User user) {
        if (!userService.checkLogin(true)) return super.BACKEND_LOGIN_REDIRECT;
        model.addAttribute(super.DIRECTORY, "backend/index/index");
        model.addAttribute(super.PAGE_TITLE_ID, "Home");
        model.addAttribute(super.USER, userService.getCurrentUser());
        return "backend/index/index-backend";
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
        return BACKEND_HOMEPAGE_REDIRECT;
    }

    /**
     * This method saves new user and sets permissions.
     *
     * @param user user object to save in the database.
     */
    private void addBasicUser(User user) {
        user.setDateCreated(new Date());
        user.setBackend(true);
        userService.save(user);
        UserType userType = new UserType("Basic");
        userTypeService.save(userType);
        permissionService.save(new Permission(user, userType, false));
        System.out.println("BackLoginWebController - Positive Result : User successfully set with basic permissions.");
    }

}
