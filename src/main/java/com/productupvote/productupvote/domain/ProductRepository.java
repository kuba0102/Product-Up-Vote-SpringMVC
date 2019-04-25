package com.productupvote.productupvote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Override
    List<Product> findAll();

    Product findById(int id);

    List<Product> findAllByNameIsContainingIgnoreCase(String name);

    List<Product> findByApprovedAndNameIsContainingIgnoreCase(String approved, String name);

    // Product filters
    List<Product> findByUserOrderByIdDesc(User user);

    List<Product> findByUserOrderByIdAsc(User user);

    List<Product> findByUserOrderByNameDesc(User user);

    List<Product> findByUserOrderByNameAsc(User user);

    List<Product> findByUserOrderByDateApprovedDesc(User user);

    List<Product> findByUserOrderByDateApprovedAsc(User user);

    List<Product> findByUserOrderByApprovedDesc(User user);

    List<Product> findByUserOrderByApprovedAsc(User user);

    List<Product> findByUserAndNameIsContainingIgnoreCaseOrderByIdDesc(User user, String name);


}
