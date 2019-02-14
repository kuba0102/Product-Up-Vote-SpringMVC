package com.productupvote.productupvote.controller;

import com.productupvote.productupvote.domain.Permission;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.domain.UserType;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.UserService;
import com.productupvote.productupvote.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ApiRestController
 * This class controls requests for:
 *
 * @author U1554969 Jakub Chruslicki
 */
@RequestMapping("/api")
@RestController
public class ApiRestController extends AppController{

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping("/all-users")
    public List<User> allUsers()
    {
        return userService.findAllUsers();
    }

    @GetMapping("/all-perms")
    public List<Permission> allPermission()
    {
        return permissionService.findAllPermissions();
    }

    @GetMapping("/all-user-types")
    public List<UserType> allUserTypes()
    {
        return userTypeService.findAll();
    }

    @GetMapping("/user-perm")
    public String userPerm(){
        System.out.println(userService.getCurrentUser().getId());
        permissionService.getUserPermissions(userService.getCurrentUser().getId());
        return "Done: check log";
    }
}
