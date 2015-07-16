package com.web.service;

import com.web.soupe.web.Permission;

import java.util.List;

/**
 * Created by nlf on 2015-7-14.
 */
public interface PermissionService {

    void save(Permission permission);
    void  delete(Integer id);
    Permission find(Integer id);

    List<Permission> findByParentId(Integer pid);
}
