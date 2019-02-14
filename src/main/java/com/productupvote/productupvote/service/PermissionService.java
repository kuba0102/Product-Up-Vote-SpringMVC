package com.productupvote.productupvote.service;

import com.productupvote.productupvote.domain.Permission;
import com.productupvote.productupvote.domain.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * This method saves new user in database.
     *
     * @param permission permission object to save in database.
     */
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }


    /**
     * This method returns all Permission.
     *
     * @return list of all Permission.
     */
    public List<Permission> findAllPermissions() {
        return permissionRepository.findAll();
    }

    /**
     * This method finds user permission by user id and returns Permission object.
     * @param id user id.
     * @return Permission object.
     */
    public Permission getUserPermissions(Integer id) {
        return permissionRepository.FindPermWithDescriptionQuery(id);
    }
}
