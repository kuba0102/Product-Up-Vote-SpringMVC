package com.productupvote.productupvote.controller;

import com.productupvote.productupvote.domain.Permission;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * UserWebController
 * This class controls requests for:
 *
 * @author U1554969 Jakub Chruslicki
 */
@RequestMapping("/backend")
@Controller
public class UserWebController extends AppController {

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
}
