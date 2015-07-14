package com.web.service;

import com.web.soupe.web.Organization;

/**
 * Created by nlf on 2015-7-14.
 */
public interface OrganizationService {

    void save(Organization organization);

    void delete(Integer id);

    Organization find(Integer id);
}
