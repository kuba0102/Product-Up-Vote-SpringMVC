package com.productupvote.productupvote.controller.backend;

import com.productupvote.productupvote.controller.AppController;
import com.productupvote.productupvote.domain.Product;
import com.productupvote.productupvote.service.ProductService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * FrontLoginWebController
 * This class controls requests for:
 * login, logout, register and delete user.
 *
 * @author U1554969 Jakub Chruslicki
 */
@RequestMapping("/backend")
@Controller
public class BackendProductWebController extends AppController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;


    /**
     * This method displays page with all to approve products.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/product-approve")
    public String displayToApproveProduct(Model model) {
        if (!userService.checkLogin(true)) return this.BACKEND_LOGIN_REDIRECT;
        model.addAttribute(super.DIRECTORY, "all-fragments/product/fragment-product-list");
        model.addAttribute(super.PAGE_TITLE_ID, "Approve Product");
        model.addAttribute("page", "approve");
        model.addAttribute("products", productService.approvedProducts("no"));
        model.addAttribute(super.USER, userService.getCurrentUser());
        return this.BACKEND_INDEX;
    }

    @GetMapping("/product-approve/{id}/{page}")
    public String updateToApproveProduct(Model model, @PathVariable("id") String id,@PathVariable("page") String page) {
        if (!userService.checkLogin(true)) return this.BACKEND_LOGIN_REDIRECT;
        System.out.println("BackendProductWebController: Approving product with id: "+ id);
        productService.update(id);
        if(page.equals("approve")) model.addAttribute("products", productService.approvedProducts("no"));
        else if(page.equals("all")) model.addAttribute("products", productService.approvedProducts("*"));
        return "all-fragments/product/fragment-product-list";
    }

    @GetMapping("/product-submitted")
    public String updateToApproveProduct(Model model) {
        if (!userService.checkLogin(true)) return this.BACKEND_LOGIN_REDIRECT;
        model.addAttribute(super.DIRECTORY, "all-fragments/product/fragment-product-list");
        model.addAttribute(super.PAGE_TITLE_ID, "Submitted Products");
        model.addAttribute("page", "all");
        model.addAttribute("products", productService.approvedProducts("*"));
        return this.BACKEND_INDEX;
    }
}
