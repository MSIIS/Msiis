package com.web.dao.Hdao;

import com.util.model.PageInfo;
import com.util.model.QueryRule;
import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.Organization;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nlf on 2015-7-14.
 */
@Component("organizationDaoH4")
public class OrganizationDaoH4 extends HBaseDao<Organization,Integer> {

    //为了生成sort_code ,重新按照顺序获取
    public List<Organization> getAllOrganizatioinForSort(){
        QueryRule queryRule=QueryRule.getInstance();
        queryRule.addAscOrder("parentId");
        queryRule.addAscOrder("id");
        return this.find(queryRule);
    }
}
