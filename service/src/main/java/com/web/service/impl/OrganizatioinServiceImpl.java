package com.web.service.impl;

import com.util.model.QueryRule;
import com.web.dao.Hdao.DaoManager;
import com.web.service.OrganizationService;
import com.web.soupe.dto.SoupeConst;
import com.web.soupe.web.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nlf on 2015-7-14.
 */
@Service("organizationServiceImpl")
@Transactional(readOnly = true)
public class OrganizatioinServiceImpl implements OrganizationService {

    @Autowired
    private DaoManager daoManager;
    @Override
    @Transactional(readOnly = false)
    public void save(Organization organization) {
       this.daoManager.getOrganizationDaoH4().save(organization);
    }
    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        this.daoManager.getOrganizationDaoH4().delete(id);

    }
    @Override
    public Organization find(Integer id) {
       return this.daoManager.getOrganizationDaoH4().get(id);
    }

    @Override
    public List<Organization> getAllList(Integer parentId) {
        QueryRule queryRule=QueryRule.getInstance();
        queryRule.addEqual("status", SoupeConst.STATUS_OK);
        queryRule.addEqual("parentId",parentId);
        return this.daoManager.getOrganizationDaoH4().find(queryRule);
    }
}
