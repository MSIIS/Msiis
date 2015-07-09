package com.web.dao.Hdao;

import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.UserRoleRelation;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2015/7/9.
 */
@Component("userRoleRelationDaoH4")
public class UserRoleRelationDaoH4 extends HBaseDao<UserRoleRelation,Long> {
}
