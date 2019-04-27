package com.productupvote.productupvote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    User findUserByEmail(String email);

    List<User> findUserByNameIsContainingIgnoreCaseOrEmailIsContainingIgnoreCaseOrSurnameIsContainingIgnoreCaseOrUsernameIsContainingIgnoreCase(String search, String search2, String search3, String search4);

    User findUserById(int userId);
}
