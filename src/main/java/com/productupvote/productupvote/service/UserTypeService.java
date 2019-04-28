package com.productupvote.productupvote.service;

import com.productupvote.productupvote.domain.UserType;
import com.productupvote.productupvote.domain.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductService
 * Service class for Products.
 * Methods:
 * save and findAll.
 *
 * @author U1554969 Jakub Chruslicki
 */
@Service
public class UserTypeService {
    @Autowired
    private UserTypeRepository userTypeRepository;

    /**
     * This method saves new user in database.
     *
     * @param userType UserType object to save in database.
     */
    public void save(UserType userType) {
        userTypeRepository.save(userType);
    }

    public List<UserType> findAll(){
        return userTypeRepository.findAll();
    }

}
