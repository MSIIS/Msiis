package com.web.dao.Hdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * soupe-dao manager  平台部分所用
 */

@Component("daoManager")
public class DaoManager {
	@Autowired
	private RollDaoH4 rollDaoH4;
	@Autowired
	private UserDaoH4 userDaoH4;

    @Autowired
    private RoleDaoH4 roleDaoH4;

    @Autowired
    private OrganizationDaoH4 organizationDaoH4;

    @Autowired
    private PermissionDaoH4 permissionDaoH4;

    @Autowired
    private UserRoleOrgRelationDaoH4 userRoleOrgRelationDaoH4;

	public RollDaoH4 getRollDaoH4() {
		return rollDaoH4;
	}

	public void setRollDaoH4(RollDaoH4 rollDaoH4) {
		this.rollDaoH4 = rollDaoH4;
	}

	public UserDaoH4 getUserDaoH4() {
		return userDaoH4;
	}

	public void setUserDaoH4(UserDaoH4 userDaoH4) {
		this.userDaoH4 = userDaoH4;
	}

    public RoleDaoH4 getRoleDaoH4() {
        return roleDaoH4;
    }

    public void setRoleDaoH4(RoleDaoH4 roleDaoH4) {
        this.roleDaoH4 = roleDaoH4;
    }

    public UserRoleOrgRelationDaoH4 getUserRoleOrgRelationDaoH4() {
        return userRoleOrgRelationDaoH4;
    }

    public void setUserRoleOrgRelationDaoH4(UserRoleOrgRelationDaoH4 userRoleRelationDaoH4) {
        this.userRoleOrgRelationDaoH4 = userRoleRelationDaoH4;
    }

    public OrganizationDaoH4 getOrganizationDaoH4() {
        return organizationDaoH4;
    }

    public void setOrganizationDaoH4(OrganizationDaoH4 organizationDaoH4) {
        this.organizationDaoH4 = organizationDaoH4;
    }

    public PermissionDaoH4 getPermissionDaoH4() {
        return permissionDaoH4;
    }

    public void setPermissionDaoH4(PermissionDaoH4 permissionDaoH4) {
        this.permissionDaoH4 = permissionDaoH4;
    }

}
