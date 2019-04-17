package com.productupvote.productupvote.service;
import com.productupvote.productupvote.domain.Product;
import com.productupvote.productupvote.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    /**
     * This method saves new product to the database.
     * @param product new product to be saved in the database.
     * @param image image for the product.
     * @throws Exception saving image to the local space.
     */
    public void save(Product product, MultipartFile image) throws Exception {
        Date date = new Date();
        String imgTime = String.valueOf(date.getTime());
        String dir = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\src\\products_img\\";
        String springPath = "src/products_img/"+ imgTime + image.getOriginalFilename();
        byte[] bytes = image.getBytes();
        Path path = Paths.get(dir + imgTime + image.getOriginalFilename());
        Files.write(path, bytes);
        product.setUser(userService.getCurrentUser());
        product.setImage(springPath);
        productRepository.save(product);
    }

    /**
     * This method returns list of products.
     * @param approved search term for approved column.
     * @return param: yes = all approved, no = not approved, wait = on wait list.
     */
    public List<Product> approvedProducts(String approved) {
        return productRepository.findByApproved(approved);
    }
}
