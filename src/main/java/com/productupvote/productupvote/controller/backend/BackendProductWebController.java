package com.productupvote.productupvote.controller.backend;

import com.productupvote.productupvote.controller.AppController;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.ProductService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BackendProductWebController
 * This class controls requests for:
 * displayToApproveProduct, updateToApproveProduct and displayAllSubmitted.
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
    @Autowired
    PermissionService permissionService;


    /**
     * This method displays page with all to approve products.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/product-approve")
    public String displayToApproveProduct(Model model) {
        if (!userService.checkLogin(true)) return this.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isProductApprove())
            return super.displayUnauthorised(model, "No permission to approve product.");
        model.addAttribute(super.DIRECTORY, "backend/product/fragment-products");
        model.addAttribute(super.PAGE_TITLE_ID, "Approve Product");
        model.addAttribute("page", "approve");
        model.addAttribute("products", productService.approvedProducts("no"));
        model.addAttribute(super.USER, userService.getCurrentUser());
        return this.BACKEND_INDEX;
    }

    /**
     * This method updates the product page.
     *
     * @param model supply attributes used for rendering views.
     * @param id    id of the product to edit.
     * @param page  which type of page its is.
     * @return directory path of the html page to render.
     */
    @GetMapping("/product-approve/{id}/{page}")
    public String updateToApproveProduct(Model model, @PathVariable("id") String id, @PathVariable("page") String page) {
        if (!userService.checkLogin(true)) return this.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isProductView())
            return super.displayUnauthorised(model, "No permission to view product.");
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isProductApprove())
            return super.displayUnauthorised(model, "No permission to approve product.");
        System.out.println("BackendProductWebController: Approving product with id: " + id);
        productService.update(id);
        if (page.equals("approve")) model.addAttribute("products", productService.approvedProducts("no"));
        else if (page.equals("all")) model.addAttribute("products", productService.approvedProducts("*"));
        return "backend/product/fragment-products";
    }

    /**
     * This method display all required products.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/product-submitted")
    public String displayAllSubmitted(Model model) {
        if (!userService.checkLogin(true)) return this.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isProductView())
            return super.displayUnauthorised(model, "No permission to view product.");
        model.addAttribute(super.DIRECTORY, "backend/product/fragment-products");
        model.addAttribute(super.PAGE_TITLE_ID, "Submitted Products");
        model.addAttribute("page", "all");
        model.addAttribute("products", productService.approvedProducts("*"));
        return this.BACKEND_INDEX;
    }
}
