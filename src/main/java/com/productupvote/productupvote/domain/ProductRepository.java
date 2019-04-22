package com.productupvote.productupvote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Override
    List<Product> findAll();

    List<Product> findAllByNameIsContainingIgnoreCase(String name);

    List<Product> findByApprovedAndNameIsContainingIgnoreCase(String approved, String name);

    Product findById(int id);

    List<Product> findByUserAndNameIsContainingIgnoreCase(User user, String name);
}
