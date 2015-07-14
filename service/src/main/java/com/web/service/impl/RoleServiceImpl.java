package com.web.service.impl;

import com.util.model.QueryRule;
import com.web.dao.Hdao.DaoManager;
import com.web.service.RoleService;
import com.web.soupe.web.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by lenovo on 2015/7/9.
 */
@Service("roleServiceImpl")
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private DaoManager daoManager;

    @Override
    @Transactional(readOnly = false)
    public void save(Role role) {
        this.daoManager.getRoleDaoH4().save(role);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.daoManager.getRoleDaoH4().delete(id);

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteByIds(Collection<Long> ids) {
        this.daoManager.getRoleDaoH4().deleteByPks(ids);
    }

    @Override
    public Role findById(Long id) {
        return this.daoManager.getRoleDaoH4().get(id);
    }

    @Override
    public List<Role> findListByIds(List<Long> ids) {
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.addIn("id", ids);
        queryRule.addEqual("status", 1);
        return this.daoManager.getRoleDaoH4().find(queryRule);
    }

    public DaoManager getDaoManager() {
        return daoManager;
    }

    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
}
