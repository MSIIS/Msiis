package com.web.dao.Hdao;

import com.web.soupe.web.UserRoleRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoManager")
public class DaoManager {
	@Autowired
	private RollDaoH4 rollDaoH4;
	@Autowired
	private UserDaoH4 userDaoH4;

    @Autowired
    private RoleDaoH4 roleDaoH4;

    @Autowired
    private UserRoleRelationDaoH4 userRoleRelationDaoH4;

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

    public UserRoleRelationDaoH4 getUserRoleRelationDaoH4() {
        return userRoleRelationDaoH4;
    }

    public void setUserRoleRelationDaoH4(UserRoleRelationDaoH4 userRoleRelationDaoH4) {
        this.userRoleRelationDaoH4 = userRoleRelationDaoH4;
    }
}
