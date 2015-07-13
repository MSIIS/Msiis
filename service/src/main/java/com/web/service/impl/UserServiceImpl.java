package com.web.service.impl;


import com.util.model.PageInfo;
import com.util.model.QueryRule;
import com.web.service.BaserService;
import com.web.service.PasswordHelper;
import com.web.service.UserService;
import com.web.soupe.web.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service("userServiceImpl")
@Transactional(readOnly=true)
public class UserServiceImpl extends BaserService implements UserService {
    @Autowired
    private PasswordHelper passwordHelper;
    @Override
    @Transactional(readOnly = false)
    public void insertBatch(List<User> userList) {
       passwordHelper.encrypPasswordUsers(userList);
       this.getDaoManager().getUserDaoH4().insertBatchH(userList);
    }
    @Override
    public User findUserByNameAndPassword(String name, String password,int status) {
        QueryRule queryRule =QueryRule.getInstance();
        queryRule.addEqual("userName",name);
        if(!StringUtils.isEmpty(password)){
            queryRule.addEqual("password",password);
        }
        if(status!=-1){
            queryRule.addEqual("status",status);
        }
        List<User>  users =this.daoManager.getUserDaoH4().find(queryRule);
        if(!CollectionUtils.isEmpty(users)){
            return users.get(0);
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        User user =this.getDaoManager().getUserDaoH4().get(id);
        return user;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
    this.getDaoManager().getUserDaoH4().delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Collection<User> users) {
        passwordHelper.encrypPasswordUsers(users);
        this.getDaoManager().getUserDaoH4().saveAll(users);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User u) {
        passwordHelper.encryptPassword(u);
        this.getDaoManager().getUserDaoH4().save(u);
    }

    @Override
    public PageInfo<User> findPageInfoRule(Map<String, String> paramMap) {
        Integer pageNo = paramMap.get("pageNo")==null?1 :Integer.valueOf(paramMap.get("pageNo"));
        Integer pageSize = paramMap.get("pageSize")==null?10 :Integer.valueOf(paramMap.get("pageSize"));
        String hql = "from User where 1=1 ";
        if(!StringUtils.isEmpty(paramMap.get("userName"))){
            hql+="  and  userName like "+paramMap.get("userName")+"%";
        }
        return this.getDaoManager().getUserDaoH4().findPageInfoBySql(hql,pageNo,pageSize,new Object[]{},null,null);
    }
}
