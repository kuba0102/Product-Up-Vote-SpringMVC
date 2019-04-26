package com.productupvote.productupvote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    User findUserByEmail(String email);

    List<User> findUserByNameLike(String search);

    User findUserById(int userId);
}
