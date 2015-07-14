package com.web.service.impl;

import com.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceManager")
public class ServiceManager{
	@Autowired
	private RollService rollService;
	@Autowired
	private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleOrgRelationService userRoleOrgRelationService;
    @Autowired
    private OrganizationService organizationService;
	public RollService getRollService() {
		return rollService;
	}
	public void setRollService(RollService rollService) {
		this.rollService = rollService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserRoleOrgRelationService getUserRoleOrgRelationService() {
        return userRoleOrgRelationService;
    }

    public void setUserRoleOrgRelationService(UserRoleOrgRelationService userRoleOrgRelationService) {
        this.userRoleOrgRelationService = userRoleOrgRelationService;
    }
}
