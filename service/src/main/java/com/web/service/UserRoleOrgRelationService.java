package com.web.service;

import com.web.soupe.web.UserRoleOrgRelation;

import java.util.List;

/**
 * Created by lenovo on 2015/7/9.
 */
public interface UserRoleOrgRelationService {
    List<UserRoleOrgRelation> findByUserId(Long userId);
    List<UserRoleOrgRelation> findByRoleId(Long roleId);
    void save(List<UserRoleOrgRelation> relations);
}
