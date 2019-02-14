package com.productupvote.productupvote.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {

    @Override
    List<Permission> findAll();
    Permission findPermissionByUser(User user);

}
