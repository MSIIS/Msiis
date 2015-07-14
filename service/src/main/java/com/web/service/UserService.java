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

    /**
     * 批量插入，sql方式
     * @param userList
     */
    void insertBatch(List<User> userList);

    /**
     * 检查注册的 名字或昵称是否存在;
     * @param userName
     * @param nickName
     * @return true:存在 ;false:不存在
     */
    boolean checkExists(String userName,String nickName);
}
