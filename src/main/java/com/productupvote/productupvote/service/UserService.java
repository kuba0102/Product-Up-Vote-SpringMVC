package com.productupvote.productupvote.service;

import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * This method saves new user in database.
     * @param user user object to save in database.
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * This method finds user by using email.
     * @param email email to log in.
     * @return user if one is found.
     */
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
