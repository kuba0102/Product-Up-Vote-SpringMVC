package com.productupvote.productupvote.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {

    @Override
    List<Permission> findAll();
    Permission findPermissionByUser(User user);

    @Query(
            value = "SELECT * FROM permissions perm " +
                    "WHERE perm.user_id = :id",
            nativeQuery = true)
    Permission FindPermWithDescriptionQuery(@Param("id") Integer id);

}
