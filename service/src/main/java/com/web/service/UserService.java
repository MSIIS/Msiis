package com.web.service;

import com.util.model.PageInfo;
import com.web.soupe.web.User;

import java.util.Collection;
import java.util.Map;


public interface UserService {

    User findUserByNameAndPassword(String name, String password);
    
    User findById(Long id);
    
    void deleteById(Long id);
    
    Collection<User> save(Collection<User> users);
    
    User save(User u);

    /**
     *  获取分页信息，实体ID需要为Int
     * @param paramMap
     * @return
     */
    PageInfo<User> findPageInfoRule(Map<String,String> paramMap);
}
