package com.productupvote.productupvote.controller.backend;

import com.productupvote.productupvote.controller.AppController;
import com.productupvote.productupvote.domain.Permission;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * BackUserWebController
 * This class controls requests for:
 * displayEditPermissionForm, updatingPermissions, displayAllUsers, displaySearchUsers, displayUpdateUserForm
 * and updateUserDetails.
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


    /**
     * This method displays permission form.
     *
     * @param model  supply attributes used for rendering views.
     * @param userId id of the user to display.
     * @return directory path of the html page to render.
     */
    @GetMapping("/permission/{userId}")
    public String displayEditPermissionForm(Model model, @PathVariable("userId") Integer userId) {
        if (!userService.checkLogin(true)) return super.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isUserEdit()) return super.displayUnauthorised(model, "No permission for user edit.");
        String[] bools = {"false", "true"};
        model.addAttribute(super.PERM, permissionService.getUserPermissions(userId));
        model.addAttribute("bools", bools);
        System.out.println("BackUserWebController: Returning Perm Form");
        return "backend/user/edit-permissions-form";
    }

    /**
     * This method set new permissions.
     *
     * @param model  supply attributes used for rendering views.
     * @param perm   Permission object that will be used to update user permissions.
     * @return directory path of the html page to render.
     */
    @PostMapping("/permission")
    public String updatingPermissions(Model model, @ModelAttribute("perm") Permission perm) {
        if (!userService.checkLogin(true)) return super.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isUserEdit())
            return super.displayUnauthorised(model, "No permission for user edit.");
        Permission tempPerm = permissionService.getUserPermissions(perm.getUser().getId());
        perm.setUserType(tempPerm.getUserType());
        permissionService.save(perm);
        model.addAttribute("message", "User updated!");
        System.out.println("BackUserWebController - Successful: Permission updated.");
        return displayAllUsers(model);
    }

    /**
     * This method returns all users.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/all-users")
    public String displayAllUsers(Model model) {
        if (!userService.checkLogin(true)) return super.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isUserView())
            return super.displayUnauthorised(model, "No permission for user view.");
        model.addAttribute(super.DIRECTORY, "backend/user/all-users");
        model.addAttribute(super.PAGE_TITLE_ID, "All Users");
        model.addAttribute("users", userService.findAllUsers());

        return "backend/index/index-backend";
    }

    /**
     * This method displays user that is search for.
     *
     * @param model  supply attributes used for rendering views.
     * @param search name of the user.
     * @return directory path of the html page to render.Ø
     */
    @PostMapping("/all-users/{search}")
    public String displaySearchUsers(Model model, @PathVariable("search") String search) {
        model.addAttribute("users", userService.userSearch(search));
        return "backend/fragments/ajax/ajax-all-users.html";
    }

    /**
     * This method logs current user out.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/update-user/{userId}")
    public String displayUpdateUserForm(Model model, @PathVariable("userId") Integer userId) {
        model.addAttribute("userToUpdate", userService.findUserById(userId));
        model.addAttribute("user", userService.getCurrentUser());
        return "backend/product/fragment-user-update-form";
    }

    /**
     * This method updates user as admin.
     * @param model supply attributes used for rendering views.
     * @param user user to update.
     * @return directory path of the html page to render.
     */
    @PostMapping("/update-user")
    public String updateUserDetails(Model model, @ModelAttribute("user") User user){
        if(!userService.checkLogin(true)) return super.LOGIN_REDIRECT;
        if(!permissionService.getCurrentUserPermission().isUserEdit()) return super.displayUnauthorised(model, "No user permissions to edit");
        User tmpUser = userService.findUserById(user.getId());
        user.setDateUpdated(new Date());
        user.setSalt(tmpUser.getSalt());
        user.setPassword(tmpUser.getPassword());
        userService.save(user);
        model.addAttribute("message", "User has been updated");

        return displayAllUsers(model);
    }
}
