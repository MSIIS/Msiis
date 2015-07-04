package com.web.service;

import com.web.soupe.web.User;

import java.util.Collection;


public interface UserService {
    User findUserByNameAndPassword(String name, String password);
    
    User findById(Long id);
    
    void deleteById(Long id);
    
    Collection<User> save(Collection<User> users);
    
    User save(User u);
}
