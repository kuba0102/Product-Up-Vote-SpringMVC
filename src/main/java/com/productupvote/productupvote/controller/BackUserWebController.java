package com.productupvote.productupvote.controller;

import com.productupvote.productupvote.domain.Permission;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * BackUserWebController
 * This class controls requests for:
 *
 * @author U1554969 Jakub Chruslicki
 */
@RequestMapping("/backend")
@Controller
public class BackUserWebController extends AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/permission/{userId}")
    public String displayeditPermissionForm(Model model, @PathVariable("userId") Integer userId) {
        if (!userService.checkLogin(true)) return super.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isUserEdit())
            return super.BACKEND_HOMEPAGE_REDIRECT;
        String[] bools = {"false", "true"};
        model.addAttribute(super.PAGE_TITLE_ID, "Set User Permission");
        model.addAttribute(super.PERM, permissionService.getUserPermissions(userId));
        model.addAttribute("bools", bools);

        return "backend/user/edit-permissions-form";
    }

    @PostMapping("/permission/{userId}")
    public String updatingPermissions(Model model, @ModelAttribute("perm") Permission perm, @PathVariable("userId") Integer userId) {
        if (!userService.checkLogin(true)) return super.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isUserEdit())
            return super.BACKEND_HOMEPAGE_REDIRECT;
        Permission tempPerm = permissionService.getUserPermissions(userId);
        perm.setUserType(tempPerm.getUserType());
        permissionService.save(perm);
        model.addAttribute("message", "User updated!");
        System.out.println("Successful: Permission updated.");
        return displayeditPermissionForm(model, userId);
    }

    @GetMapping("/all-users")
    public String displayAllUsers(Model model) {
        if (!userService.checkLogin(true)) return super.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isUserView())
            return super.BACKEND_HOMEPAGE_REDIRECT;
        model.addAttribute(super.PAGE_TITLE_ID, "All Users");

        model.addAttribute("users", userService.findAllUsers());


        return "backend/user/all-users";
    }

    @PostMapping("/all-users/{search}")
    public String displaySearchUsers(Model model, @PathVariable("search") String search) {

        System.out.println(search);
        model.addAttribute("users", userService.userSearch(search));
        return "backend/fragments/ajax/ajax-all-users.html";
    }
}
