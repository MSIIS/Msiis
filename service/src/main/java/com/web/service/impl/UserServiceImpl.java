package com.web.service.impl;


import com.util.model.PageInfo;
import com.util.model.QueryRule;
import com.web.service.BaserService;
import com.web.service.UserService;
import com.web.soupe.web.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Service("userServiceImpl")
@Transactional(readOnly=true)
public class UserServiceImpl extends BaserService implements UserService {


    @Override
    @Transactional(readOnly = false)
    public void insertBatch(List<User> userList) {
       this.getDaoManager().getUserDaoH4().insertBatchH(userList);
    }
    @Override
    public User findUserByNameAndPassword(String name, String password) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return this.getDaoManager().getUserDaoH4().get(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Collection<User> save(Collection<User> users) {
        return null;
    }

    @Override
    public User save(User u) {
        return null;
    }

    @Override
    public PageInfo<User> findPageInfoRule(Map<String, String> paramMap) {
//        QueryRule queryRule = QueryRule.getInstance();
//        queryRule.addNotEqual("id",0);
//        queryRule.addAscOrder("id");
        Integer pageNo=Integer.valueOf(paramMap.get("pageNo"));
        Integer pageSize = Integer.valueOf(paramMap.get("pageSize"));
//        return this.getDaoManager().getUserDaoH4().findPageInfo(queryRule,pageNo,pageSize);
        String hql = "from User where 1=1 and id > ? ";
        return this.getDaoManager().getUserDaoH4().findPageInfoBySql(hql,pageNo,pageSize,new Object[]{0L},null,null);
    }
}
