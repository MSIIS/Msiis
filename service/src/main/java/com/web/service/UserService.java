package com.web.service;

import com.util.model.PageInfo;
import com.web.soupe.web.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface UserService {

    User findUserByNameAndPassword(String name, String password,int status);
    
    User findById(Long id);
    
    void deleteById(Long id);
    
    void save(Collection<User> users);
    
    void save(User u);

    /**
     *  获取分页信息，实体ID需要为Int
     * @param paramMap
     * @return
     */
    PageInfo<User> findPageInfoRule(Map<String,String> paramMap);

    void insertBatch(List<User> userList);
}
