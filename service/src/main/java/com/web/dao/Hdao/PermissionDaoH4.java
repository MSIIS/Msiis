package com.web.dao.Hdao;

import com.util.model.QueryRule;
import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.dto.SoupeConst;
import com.web.soupe.web.Permission;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by nlf on 2015-7-14.
 */
@Component("permissionDaoH4")
public class PermissionDaoH4 extends HBaseDao<Permission,Integer> {
    public List<Permission> findByParentId(Integer pid){
        QueryRule queryRule =QueryRule.getInstance();
        queryRule.addEqual("parentId",pid);
        queryRule.addAscOrder("sortNum");
        queryRule.addEqual("status", SoupeConst.STATUS_OK);
        queryRule.addEqual("deleted",false);
        return this.find(queryRule);
    };
}
