package com.web.dao.Hdao;

import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.Permission;
import org.springframework.stereotype.Component;

/**
 * Created by nlf on 2015-7-14.
 */
@Component("permissionDaoH4")
public class PermissionDaoH4 extends HBaseDao<Permission,Integer> {
}
