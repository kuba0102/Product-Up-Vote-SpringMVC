package com.productupvote.productupvote.controller.frontend;

import com.productupvote.productupvote.controller.AppController;
import com.productupvote.productupvote.controller.FilterWebController;
import com.productupvote.productupvote.domain.Offer;
import com.productupvote.productupvote.domain.Product;
import com.productupvote.productupvote.service.ProductService;
import com.productupvote.productupvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * FrontLoginWebController
 * This class controls requests for:
 * displayProductForm, displayProductForm, displayProducts, displayMyProducts, searchProduct, addVote,
 * updateUserApproveProduct, displayMyUpVotedProducts and removeProduct.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Controller
public class FrontProductWebController extends AppController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    FilterWebController filterWebController;


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
                                     @RequestParam("imageFile") MultipartFile image,
                                     @RequestParam("marketPrice") double marketPrice,
                                     @RequestParam("sourcePrice") double sourcePrice) {
        if (!userService.checkLogin(false)) return this.LOGIN_REDIRECT;
        if (!image.getContentType().equals("image/png")) {
            model.addAttribute("product", product);
            model.addAttribute("error", "Only PNG images accepted.");
            return displayProductForm(model);
        }
        if (!userService.checkCurrentUserVotes(5)) {
            model.addAttribute("product", product);
            model.addAttribute("error", "Not enough votes.");
            return displayProductForm(model);
        }
        try {
            System.out.println("FrontProductWebController: Submitting Product: " + product.getName());
            // Adding offer to product.
            List<Offer> offers = new ArrayList<>();
            Offer offer = new Offer();
            offer.setComment("New Submission");
            offer.setMarketPrice(marketPrice);
            offer.setSourcePrice(sourcePrice);
            offer.setProduct(product);
            offers.add(offer);
            offer.setUser(userService.getCurrentUser());
            product.setOffers(offers);
            userService.addVotes("-", 5, userService.getCurrentUser().getId());
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
        model.addAttribute("page", "approved");
        model.addAttribute("url", "/product-search/");
        model.addAttribute("products", productService.approvedProducts("yes", true, "", null, null));
        return this.FRONTEND_INDEX;
    }

    /**
     * This method display my product page for users.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/product-my")
    public String displayMyProducts(Model model) {
        if (!userService.checkLogin(false)) return this.LOGIN_REDIRECT;
        model.addAttribute(super.DIRECTORY, "frontend/product/product-my-products");
        model.addAttribute(super.PAGE_TITLE_ID, "My Products");
        model.addAttribute(super.USER, userService.getCurrentUser());
        model.addAttribute("page", "my");
        model.addAttribute("url", "/product-search/");
        model.addAttribute("offer", new Offer());
        model.addAttribute("products", productService.myProducts("", null, null));

        return this.FRONTEND_INDEX;
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
        List<Product> products = null;
        model.addAttribute("offer", new Offer());
        if (searchType.equals("approved")) {
            model.addAttribute("products", productService.approvedProducts("yes", true, search, null, null));
            return "all-fragments/product/fragment-product-list-index";
        } else if (searchType.equals("my")) {
            model.addAttribute("products", productService.myProducts(search, null, null));
            return "all-fragments/product/fragment-product-list-2";
        } else if (searchType.equals("myUpvoted")) {
            model.addAttribute("products", productService.myUpVotedProducts(search, null, null));
            return "all-fragments/product/fragment-product-list-index";
        }
        return "all-fragments/product/fragment-product-list-index";
    }

    /**
     * This method up votes product and refreshes product list.
     *
     * @param model      supply attributes used for rendering views.
     * @param productId  id of the product to up vote.
     * @param searchType type product to search.
     * @param search     product search term.
     * @param filter     product filter to apply.
     * @param descAsc    product order to apply.
     * @return directory path of the html page to render.
     */
    @PostMapping("/up-vote/{id}/{searchType}/{search}/{filter}/{descAsc}")
    public String addVote(Model model, @PathVariable("id") int productId,
                          @PathVariable("searchType") String searchType,
                          @PathVariable("search") String search,
                          @PathVariable("filter") String filter,
                          @PathVariable("descAsc") String descAsc) {
        if (!userService.checkLogin(false)) return this.LOGIN_REDIRECT;
        if (search.equals("null")) search = "";
        userService.addMyUpVoted(productId);
        System.out.println("FrontProductWebController: UpVoting Product" + "Product id: " + productId);
        productService.addVote(productId);
        return filterWebController.filterProduct(model, searchType, search, filter, descAsc);
    }

    /**
     * This method updates the product page.
     *
     * @param model supply attributes used for rendering views.
     * @param id    id of the product to edit.
     * @param page  which type of page its is.
     * @return directory path of the html page to render.
     */
    @PostMapping("/product/product-approve/{id}/{page}/{search}/{approveStatus}")
    public String updateUserApproveProduct(Model model, @PathVariable("id") String id,
                                           @PathVariable("page") String page,
                                           @PathVariable("search") String search,
                                           @PathVariable String approveStatus) {
        if (!userService.checkLogin(false)) return this.BACKEND_LOGIN_REDIRECT;
        if (search.equals("null")) search = "";
        System.out.println("FrontProductWebController: User approving product with id: " + id);
        productService.updateApproveStatus(id, false, approveStatus);
        if (page.equals("my"))
            model.addAttribute("products", productService.myProducts(search, null, null));
        model.addAttribute("offer", new Offer());
        return "all-fragments/product/fragment-product-list-2";
    }

    /**
     * This method display home page will accepted products.
     *
     * @param model supply attributes used for rendering views.
     * @return directory path of the html page to render.
     */
    @GetMapping("/my-up-voted")
    public String displayMyUpVotedProducts(Model model) {
        if (!userService.checkLogin(false)) return this.LOGIN_REDIRECT;
        model.addAttribute(super.DIRECTORY, "frontend/product/product-my-upvoted");
        model.addAttribute(super.PAGE_TITLE_ID, "MyUpVoted");
        model.addAttribute(super.USER, userService.getCurrentUser());
        model.addAttribute("page", "myUpvoted");
        model.addAttribute("url", "/product-search/");
        model.addAttribute("products", productService.myUpVotedProducts("", null, ""));
        System.out.println("FrontProductWebController: Getting my up voted products");
        return this.FRONTEND_INDEX;
    }

    /**
     * This method removes product and displays correct page.
     * @param model supply attributes used for rendering views.
     * @param productId id of the product to remove.
     * @return directory path of the html page to render.
     */
    @PostMapping("/product/remove/{productId}")
    public String removeProduct(Model model, @PathVariable Integer productId) {
        try {
            Product product = productService.findById(productId);
            if (product.getUser().getId() == userService.getCurrentUser().getId()) {
                productService.remove(product);
                System.out.println("FrontProductWebController: Product removed");
            }
            return "redirect:/product-my";
        } catch (Exception e) {
            System.out.println(e);
            return displayMyProducts(model);
        }
    }
}