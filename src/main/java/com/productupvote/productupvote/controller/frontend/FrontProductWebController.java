package com.productupvote.productupvote.controller.frontend;

import com.productupvote.productupvote.controller.AppController;
import com.productupvote.productupvote.domain.Product;
import com.productupvote.productupvote.service.ProductService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    ProductService productService;


    /**
     * This method display product form.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/submit-product")
    public String displayProductForm(Model model) {
        if (!userService.checkLogin(false)) return this.LOGIN_REDIRECT;
        model.addAttribute(super.DIRECTORY, "frontend/product/product-submit");
        model.addAttribute(super.PAGE_TITLE_ID, "Submit Product");
        model.addAttribute("product", new Product());
        model.addAttribute(super.USER, userService.getCurrentUser());
        return this.FRONTEND_INDEX;
    }

    /**
     * This method collects all the data and validates it.
     *
     * @param model   supply attributes used for rendering views.
     * @param product new product to be saved in the database.
     * @param image   image for the product.
     * @return directory path of the html page to render.
     */
    @PostMapping("/submit-product")
    public String displayProductForm(Model model, @ModelAttribute("product") Product product,
                                     @RequestParam("imageFile") MultipartFile image) {
        if (!userService.checkLogin(false)) return this.LOGIN_REDIRECT;
        if (!image.getContentType().equals("image/png")) {
            model.addAttribute("error", "Only PNG images accepted.");
            return displayProductForm(model);
        }
        try {
            System.out.println("FrontProductWebController: Submitting Product: " + product.getName());
            productService.save(product, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("msg", "Product Submitted");
        return displayProductForm(model);
    }

    /**
     * This method display home page will accepted products.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/")
    public String displayProducts(Model model) {
        model.addAttribute(super.DIRECTORY, "frontend/index/index-products");
        model.addAttribute(super.PAGE_TITLE_ID, "ProductUpVote");
        model.addAttribute(super.USER, userService.getCurrentUser());
        model.addAttribute("products", productService.approvedProducts("yes"));
        for(Product p : productService.approvedProducts("yes")){
            System.out.println(p.getImage());
        }
        return this.FRONTEND_INDEX;
    }

}
