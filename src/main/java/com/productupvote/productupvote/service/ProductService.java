package com.productupvote.productupvote.service;

import com.productupvote.productupvote.domain.Product;
import com.productupvote.productupvote.domain.ProductRepository;
import com.productupvote.productupvote.domain.User;
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
     *
     * @param product new product to be saved in the database.
     * @param image   image for the product.
     * @throws Exception saving image to the local space.
     */
    public void save(Product product, MultipartFile image) throws Exception {
        Date date = new Date();
        String imgTime = String.valueOf(date.getTime());
        String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\src\\products_img\\";
        String springPath = "src/products_img/" + imgTime + image.getOriginalFilename();
        byte[] bytes = image.getBytes();
        Path path = Paths.get(dir + imgTime + image.getOriginalFilename());
        Files.write(path, bytes);
        product.setUser(userService.getCurrentUser());
        product.setImage(springPath);
        productRepository.save(product);
    }

    /**
     * This method returns list of products.
     *
     * @param approved search term for approved column.
     * @return param: yes = all approved, no = not approved, wait = on wait list.
     */
    public List<Product> approvedProducts(String approved, String search) {
        if (approved.equals("*")) return productRepository.findAllByNameIsContainingIgnoreCase(search);
        return productRepository.findByApprovedAndNameIsContainingIgnoreCase(approved, search);
    }

    /**
     * This method updates approve column in the row.
     *
     * @param id product id to update approve status.
     */
    public void updateApproveStatus(String id) {
        Product product = productRepository.findById(Integer.parseInt(id));
        product.setDateApproved(new Date());
        product.setApproved("yes");
        productRepository.save(product);
    }

    /**
     * This method gets certain users products.
     *
     * @param id     user id to find products for.
     * @param search search term.
     * @return list of products.
     */
    public List<Product> myProducts(String id, String search, String filter, String descAsc) {

        User user = userService.getCurrentUser();
        if (filter != null) {
            if (filter.equals("id")) {
                if (descAsc.equals("desc")) return productRepository.findByUserOrderByIdDesc(user);
                return productRepository.findByUserOrderByIdAsc(user);
            } else if (filter.equals("name")) {
                if (descAsc.equals("desc")) return productRepository.findByUserOrderByNameDesc(user);
                return productRepository.findByUserOrderByNameAsc(user);
            } else if (filter.equals("approved")) {
                if (descAsc.equals("desc")) return productRepository.findByUserOrderByApprovedDesc(user);
                return productRepository.findByUserOrderByApprovedAsc(user);
            } else if (filter.equals("dateApproved")) {
                if (descAsc.equals("desc")) return productRepository.findByUserOrderByDateApprovedDesc(user);
                return productRepository.findByUserOrderByDateApprovedAsc(user);
            }
        }
        return productRepository.findByUserAndNameIsContainingIgnoreCaseOrderByIdDesc(user, search);
    }

    /**
     * This method check if user has enough votes, votes up product.
     *
     * @param productId id of the product to up vote.
     * @return true if voted  up and false if not.
     */
    public boolean addVote(int productId) {
        User user = userService.getCurrentUser();
        if (user.getVotes() != 0) {
            Product product = productRepository.findById(productId);
            product.setUpVotes(product.getUpVotes() + 1);
            user.setVotes(user.getVotes() - 1);
            productRepository.save(product);
            userService.save(user);
            return true;
        }
        return false;
    }
}
