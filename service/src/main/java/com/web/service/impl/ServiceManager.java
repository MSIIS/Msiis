package com.web.service.impl;

import com.web.service.RoleService;
import com.web.service.SsqService;
import com.web.service.UserRoleRelationService;
import com.web.service.UserService;
import com.web.soupe.web.UserRoleRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("serviceManager")
public class ServiceManager{
	@Autowired
	private SsqService ssqService ;
	@Autowired
	private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
	public SsqService getSsqService() {
		return ssqService;
	}
	public void setSsqService(SsqService ssqService) {
		this.ssqService = ssqService;
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

    public UserRoleRelationService getUserRoleRelationService() {
        return userRoleRelationService;
    }

    public void setUserRoleRelationService(UserRoleRelationService userRoleRelationService) {
        this.userRoleRelationService = userRoleRelationService;
    }
}
