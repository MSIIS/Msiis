package com.web.service.impl;

import com.util.model.QueryRule;
import com.web.dao.Hdao.DaoManager;
import com.web.service.UserRoleRelationService;
import com.web.soupe.web.UserRoleRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2015/7/9.
 */
@Service("userRoleRelationServiceImpl")
@Transactional(readOnly = true)
public class UserRoleRelationServiceImpl implements UserRoleRelationService{

    @Autowired
    private DaoManager daoManager;
    @Override
    public List<UserRoleRelation> findByUserId(Long userId) {
        QueryRule queryRule =QueryRule.getInstance();
        queryRule.addEqual("user",userId);
        return this.daoManager.getUserRoleRelationDaoH4().find(queryRule);
    }

    @Override
    public List<UserRoleRelation> findByRoleId(Long roleId) {
        QueryRule queryRule =QueryRule.getInstance();
        queryRule.addEqual("role",roleId);
        return this.daoManager.getUserRoleRelationDaoH4().find(queryRule);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(List<UserRoleRelation> relations) {
        this.daoManager.getUserRoleRelationDaoH4().saveAll(relations);
    }

    public DaoManager getDaoManager() {
        return daoManager;
    }

    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
}
