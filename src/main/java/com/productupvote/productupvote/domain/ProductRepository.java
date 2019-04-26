package com.productupvote.productupvote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Override
    List<Product> findAll();

    Product findById(int id);

    List<Product> findAllByNameIsContainingIgnoreCase(String name);

    List<Product> findByApprovedAndUserApprovedAndNameIsContainingIgnoreCase(String approved, boolean userApproved, String name);

    List<Product> findByApprovedAndNameIsContainingIgnoreCase(String approved, String search);

    // Product filters
    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByIdDesc(User user, String name);

    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByIdAsc(User user, String name);

    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByNameDesc(User user, String name);

    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByNameAsc(User user, String name);

    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByDateApprovedDesc(User user, String name);

    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByDateApprovedAsc(User user, String name);

    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByApprovedDesc(User user, String name);

    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByApprovedAsc(User user, String name);
    
    List<Product> findByApprovedAndUserApprovedAndNameIsContainingIgnoreCaseOrderByUpVotesDesc(String approved, Boolean userApproved, String search);

    List<Product> findByApprovedAndUserApprovedAndNameIsContainingIgnoreCaseOrderByUpVotesAsc(String approved, boolean userApproved, String search);

    List<Product> findByApprovedAndUserApprovedAndNameIsContainingIgnoreCaseOrderByNameDesc(String approved, boolean userApproved, String search);

    List<Product> findByApprovedAndUserApprovedAndNameIsContainingIgnoreCaseOrderByNameAsc(String approved, boolean userApproved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByUpVotesDesc(String approved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByUpVotesAsc(String approved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByNameDesc(String approved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByNameAsc(String approved, String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByIdDesc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByIdAsc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByNameDesc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByNameAsc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByDateApprovedDesc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByDateApprovedAsc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByApprovedDesc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByApprovedAsc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByUpVotesDesc(String search);

    List<Product> findByNameIsContainingIgnoreCaseOrderByUpVotesAsc(String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByIdDesc(String approved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByIdAsc(String approved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByDateApprovedDesc(String approved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByDateApprovedAsc(String approved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByApprovedDesc(String approved, String search);

    List<Product> findByApprovedAndNameIsContainingIgnoreCaseOrderByApprovedAsc(String approved, String search);
}
