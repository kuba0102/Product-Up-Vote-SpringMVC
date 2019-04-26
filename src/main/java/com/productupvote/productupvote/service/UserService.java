package com.productupvote.productupvote.service;

import com.productupvote.productupvote.domain.User;
import com.productupvote.productupvote.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    /**
     * This method returns all users.
     * @return list of all users.
     */
    public List<User> findAllUsers() {
       return userRepository.findAll();
    }

    public HttpSession getSession(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        return session;
    }
    /**
     * This method looks for User in session and returns User.
     *
     * @return User from the session.
     */
    public User getCurrentUser() {
        HttpSession session = getSession();
        return (User) session.getAttribute("user");
    }

    /**
     * This method checks if User is logged in and returns true or false.
     * It also takes boolean param which will make the method also check if user has backend access.
     * @param backend boolean param to check for backend permissions.
     * @return returns false if user is not logged in and true if User is.
     */
    public boolean checkLogin(Boolean backend) {
        User user = getCurrentUser();
        if(backend)
        {
            if(user!= null){
                if(user.isBackend()) return true; // returns true if user is logged in and has backend access.
            }else return false;
        }
        return user != null;
    }

    /**
     * This method sets user search.
     * @param search name to search for.
     * @return returns list of found users.
     */
    public List<User> userSearch(String search) {
        return userRepository.findUserByNameLike(search);
    }

    /**
     * Setts new user in session.
     * @param user user to set into session.
     */
    public void setUserSession(User user) {
        HttpSession session = getSession();
        session.setAttribute("user", user);
    }

    /**
     * This method invalidates current session.
     */
    public void cleanSession(){
        HttpSession session = getSession();
        session.invalidate();
    }

    /**
     * Find user by id.
     * @param userId user id.
     * @return user.
     */
    public User findUserById(Integer userId) {
        return userRepository.findUserById(userId);
    }
}
