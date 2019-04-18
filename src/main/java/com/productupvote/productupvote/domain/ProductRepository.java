package com.productupvote.productupvote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Override
    List<Product> findAll();
    List<Product> findByApproved(String approved);
    Product findById(int id);
    List<Product> findByUser(User id);

}
