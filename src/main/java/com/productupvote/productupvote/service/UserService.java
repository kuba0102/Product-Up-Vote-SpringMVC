package com.productupvote.productupvote.service;

import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void save(User user) {
        userRepository.save(user);
    }
}
