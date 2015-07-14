package com.web.service;

import com.web.soupe.web.Organization;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by nlf on 2015-7-14.
 */
public interface OrganizationService {

    void save(Organization organization);

    void delete(Integer id);

    Organization find(Integer id);

    /**
     * 获取所有正常的组织机构
     * @return
     */
    List<Organization> getAllList(Integer parentId);

    /**
     * 为了排序
     * @return
     */
    List<Organization> getListForSort();

    void saveAll(Collection<Organization> orgs);
}
