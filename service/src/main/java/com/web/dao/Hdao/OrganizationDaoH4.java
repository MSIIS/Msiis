package com.web.dao.Hdao;

import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.Organization;
import org.springframework.stereotype.Component;

/**
 * Created by nlf on 2015-7-14.
 */
@Component("organizationDaoH4")
public class OrganizationDaoH4 extends HBaseDao<Organization,Integer> {
}
