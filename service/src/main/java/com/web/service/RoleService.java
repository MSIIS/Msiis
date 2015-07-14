package com.web.service;

import com.web.soupe.web.Role;

import java.util.Collection;
import java.util.List;

/**
 * Created by lenovo on 2015/7/9.
 */
public interface RoleService {
    Role findById(Long id);

    List<Role> findListByIds(List<Long> ids);

    void save(Role role);

    void delete(Long id);

    void deleteByIds(Collection<Long> ids);
}
