package com.productupvote.productupvote.controller.backend;

import com.productupvote.productupvote.controller.AppController;
import com.productupvote.productupvote.domain.Offer;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.ProductService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class BackProductWebController extends AppController {

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
        model.addAttribute("url", "/backend/product-search/");
        model.addAttribute("products", productService.approvedProducts("no",false, ""));
        model.addAttribute(super.USER, userService.getCurrentUser());
        model.addAttribute("offer", new Offer());
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
    @PostMapping("/product-approve/{id}/{page}")
    public String updateToApproveProduct(Model model, @PathVariable("id") String id, @PathVariable("page") String page) {
        if (!userService.checkLogin(true)) return this.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isProductView())
            return super.displayUnauthorised(model, "No permission to view product.");
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isProductApprove())
            return super.displayUnauthorised(model, "No permission to approve product.");
        System.out.println("BackendProductWebController: Approving product with id: " + id);
        productService.updateApproveStatus(id, true);
        if (page.equals("approve")) model.addAttribute("products", productService.approvedProducts("no", false,""));
        else if (page.equals("all")) model.addAttribute("products", productService.approvedProducts("*", true,""));
        model.addAttribute("offer", new Offer());
        return "all-fragments/product/fragment-product-list";
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
        model.addAttribute("url", "/backend/product-search/");
        model.addAttribute("products", productService.approvedProducts("*", true,""));
        model.addAttribute("offer", new Offer());
        return this.BACKEND_INDEX;
    }

    /**
     * This method searches for product and returns a list of products.
     *
     * @param model      supply attributes used for rendering views.
     * @param searchType type product to search.
     * @param search     product search term.
     * @return directory path of the html page to render.
     */
    @PostMapping("/product-search/{searchType}/{search}")
    public String searchProduct(Model model, @PathVariable("searchType") String searchType, @PathVariable("search") String search) {
        if (!userService.checkLogin(true)) return this.BACKEND_LOGIN_REDIRECT;
        if (!permissionService.getUserPermissions(userService.getCurrentUser().getId()).isProductView())
            return super.displayUnauthorised(model, "No permission to view product.");
        if (searchType.equals("approve")) {
            model.addAttribute("products", productService.approvedProducts("no", false, search));
        }
        if (searchType.equals("all")) model.addAttribute("products", productService.approvedProducts("*", true, search));
        model.addAttribute("offer", new Offer());
        return "all-fragments/product/fragment-product-list";
    }
}
