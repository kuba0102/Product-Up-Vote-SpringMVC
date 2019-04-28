package com.productupvote.productupvote.controller;

import com.productupvote.productupvote.domain.Offer;
import com.productupvote.productupvote.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * FilterWebController
 * This class controls requests for:
 * filterProduct.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Controller
public class FilterWebController extends AppController {

    @Autowired
    ProductService productService;

    /**
     * @param model      supply attributes used for rendering views.
     * @param searchType type product to search.
     * @param search     product search term.
     * @param filter     product filter to apply.
     * @param descAsc    product order to apply.
     * @return directory path of the html page to render.
     */
    @PostMapping("/filter/{searchType}/{search}/{id}/{descAsc}")
    public String filterProduct(Model model,
                                @PathVariable("searchType") String searchType,
                                @PathVariable("search") String search,
                                @PathVariable("id") String filter,
                                @PathVariable("descAsc") String descAsc) {
        System.out.println("FrontProductWebController: Applying filter");
        model.addAttribute("offer", new Offer());
        if (search.equals("null")) search = "";
        if (searchType.equals("my")) {
            System.out.println("FrontProductWebController: Filters for my products");
            model.addAttribute("products", productService.myProducts(search, filter, descAsc));
        } else if (searchType.equals("approved")) {
            System.out.println("FrontProductWebController: Filters for approved products");
            model.addAttribute("products", productService.approvedProducts("yes", true, search, filter, descAsc));
            return "all-fragments/product/fragment-product-list-index";
        } else if (searchType.equals("all")) {
            System.out.println("FrontProductWebController: Filters for all products");
            model.addAttribute("products", productService.approvedProducts("*", true, search, filter, descAsc));
            return "all-fragments/product/fragment-product-list";
        } else if (searchType.equals("approve")) {
            System.out.println("FrontProductWebController: Filters for approve products");
            model.addAttribute("products", productService.approvedProducts("no", true, search, filter, descAsc));
            return "all-fragments/product/fragment-product-list";
        } else if (searchType.equals("myUpvoted")){
            System.out.println("FrontProductWebController: Filters for my up voted products");
            model.addAttribute("products", productService.myUpVotedProducts(search, filter, descAsc));
            return "all-fragments/product/fragment-product-list-index";
        }

        return "all-fragments/product/fragment-product-list-2";
    }
}
