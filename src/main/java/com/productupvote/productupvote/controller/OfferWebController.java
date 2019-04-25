package com.productupvote.productupvote.controller;

import com.productupvote.productupvote.controller.backend.BackProductWebController;
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
 * This class controls requests for:
 * displayToApproveProduct, updateToApproveProduct and displayAllSubmitted.
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


    @PostMapping("/offer")
    public String addOffer(Model model, @ModelAttribute("offer") Offer offer, @RequestParam("productId") Integer productId, @RequestParam("page") String page) {
        offer.setUser(userService.getCurrentUser());
        offer.setProduct(productService.findById(productId));
        offerService.save(offer);
        if(page.equals("all")) return backProductWebController.displayAllSubmitted(model);
        else if(page.equals("approve")) return backProductWebController.displayToApproveProduct(model);
        return this.HOMEPAGE_REDIRECT;
    }
}
