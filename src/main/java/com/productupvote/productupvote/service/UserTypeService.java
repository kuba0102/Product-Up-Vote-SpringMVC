package com.productupvote.productupvote.service;

import com.productupvote.productupvote.domain.Permission;
import com.productupvote.productupvote.domain.PermissionRepository;
import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    /**
     * This method saves new user in database.
     * @param user user object to save in database.
     */
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }


    /**
     * This method returns all Permission.
     * @return list of all Permission.
     */
    public List<Permission> findAllPermmsions() {
       return permissionRepository.findAll();
    }

}
