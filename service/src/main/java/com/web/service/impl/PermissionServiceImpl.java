package com.web.service.impl;

import com.web.dao.Hdao.DaoManager;
import com.web.service.PermissionService;
import com.web.soupe.web.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nlf on 2015-7-14.
 */
@Service("permissionServiceImpl")
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private DaoManager daoManager;

    @Override
    @Transactional(readOnly = false)
    public void save(Permission permission) {
      this.daoManager.getPermissionDaoH4().save(permission);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
      this.daoManager.getPermissionDaoH4().delete(id);
    }

    @Override
    public Permission find(Integer id) {
        return this.daoManager.getPermissionDaoH4().get(id);
    }
}
