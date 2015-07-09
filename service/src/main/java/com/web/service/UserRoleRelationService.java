package com.web.service;

import com.web.soupe.web.UserRoleRelation;

import java.util.List;

/**
 * Created by lenovo on 2015/7/9.
 */
public interface UserRoleRelationService {
    List<UserRoleRelation> findByUserId(Long userId);
    List<UserRoleRelation> findByRoleId(Long roleId);
    void save(List<UserRoleRelation> relations);
}
