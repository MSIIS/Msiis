package com.web.service;

import com.web.dao.Hdao.DaoManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by nlf on 2015-7-6.
 */
public class BaserService {
    @Autowired
    protected DaoManager daoManager;
    public DaoManager getDaoManager() {
        return daoManager;
    }

    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
}
