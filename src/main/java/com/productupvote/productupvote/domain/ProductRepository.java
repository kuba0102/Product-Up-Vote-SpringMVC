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
}
