package com.web.service.impl;

import com.util.model.QueryRule;
import com.web.dao.Hdao.DaoManager;
import com.web.service.UserRoleOrgRelationService;
import com.web.soupe.web.UserRoleOrgRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2015/7/9.
 */
@Service("userRoleOrgRelationServiceImpl")
@Transactional(readOnly = true)
public class UserRoleOrgRelationServiceImpl implements UserRoleOrgRelationService{

    @Autowired
    private DaoManager daoManager;
    @Override
    public List<UserRoleOrgRelation> findByUserId(Long userId) {
        QueryRule queryRule =QueryRule.getInstance();
        queryRule.addEqual("user",userId);
        return this.daoManager.getUserRoleOrgRelationDaoH4().find(queryRule);
    }

    @Override
    public List<UserRoleOrgRelation> findByRoleId(Long roleId) {
        QueryRule queryRule =QueryRule.getInstance();
        queryRule.addEqual("role",roleId);
        return this.daoManager.getUserRoleOrgRelationDaoH4().find(queryRule);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(List<UserRoleOrgRelation> relations) {
        this.daoManager.getUserRoleOrgRelationDaoH4().saveAll(relations);
    }

    public DaoManager getDaoManager() {
        return daoManager;
    }

    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
}
