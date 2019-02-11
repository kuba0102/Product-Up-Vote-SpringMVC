package com.productupvote.productupvote.controller;

import com.appsdeveloperblog.encryption.PassUtil;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
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
    UserService userService;

    @GetMapping("/all-users")
    public List<User> allUsers()
    {
        return userService.findAllUsers();
    }

}
