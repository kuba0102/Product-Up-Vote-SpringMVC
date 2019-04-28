package com.productupvote.productupvote.controller;

import com.productupvote.productupvote.controller.backend.BackProductWebController;
import com.productupvote.productupvote.controller.frontend.FrontProductWebController;
import com.productupvote.productupvote.domain.Offer;
import com.productupvote.productupvote.domain.Product;
import com.productupvote.productupvote.service.OfferService;
import com.productupvote.productupvote.service.PermissionService;
import com.productupvote.productupvote.service.ProductService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * OfferWebController
 * This class controls requests controls product offers:
 * addOffer.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Controller
public class OfferWebController extends AppController {

    @Autowired
    UserService userService;
    @Autowired
    OfferService offerService;
    @Autowired
    ProductService productService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    BackProductWebController backProductWebController;
    @Autowired
    FrontProductWebController frontProductWebController;


    /**
     * This method add offer to product.
     * @param model supply attributes used for rendering views.
     * @param offer new offer for the product.
     * @param productId id of the product
     * @param page page to redirect to.
     * @return directory path of the html page to render.
     */
    @PostMapping("/offer")
    public String addOffer(Model model, @ModelAttribute("offer") Offer offer, @RequestParam("productId") Integer productId, @RequestParam("page") String page) {
        if (!userService.checkLogin(false)) return this.LOGIN_REDIRECT;
        if (!permissionService.getCurrentUserPermission().isProductApprove()) return super.displayUnauthorised(model, "No permission to approve product.");
        Product product = productService.findById(productId);
        if(userService.getCurrentUser().getId() == product.getUser().getId()) {
            product.setUserApproved(true);
            product.setApproved("no");
        } else {
            product.setApproved("yes");
            product.setUserApproved(false);
        }
        offer.setUser(userService.getCurrentUser());
        offer.setProduct(product);
        offerService.save(offer);
        if(page.equals("all")) return backProductWebController.displayAllSubmitted(model);
        else if(page.equals("approve")) return backProductWebController.displayToApproveProduct(model);
        else if(page.equals("my")) return frontProductWebController.displayMyProducts(model);
        System.out.println("OfferWebController: Adding offer");
        return this.HOMEPAGE_REDIRECT;
    }
}
