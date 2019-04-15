package com.productupvote.productupvote.controller.frontend;

import com.appsdeveloperblog.encryption.PassUtil;
import com.productupvote.productupvote.controller.AppController;
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
import java.util.Date;

/**
 * FrontLoginWebController
 * This class controls requests for:
 * login, logout, register and delete user.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Controller
public class FrontProductWebController extends AppController {

    @Autowired
    UserService userService;


    @GetMapping("/submit-product")
    public String displayProductForm(Model model, User user) {
        model.addAttribute(super.PAGE_TITLE_ID, "Register");
        model.addAttribute(super.USER, user);
        return "frontend/register/register";
    }


    @GetMapping("/")
    public String displayProducts(Model model, User user) {
        model.addAttribute(super.DIRECTORY, "frontend/index/index-products");
        model.addAttribute(super.PAGE_TITLE_ID, "ProductUpVote");
        model.addAttribute(super.USER, user);
        return "frontend/index/index-frontend";
    }

}
