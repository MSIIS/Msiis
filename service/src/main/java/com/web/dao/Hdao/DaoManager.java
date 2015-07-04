package com.web.dao.Hdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("daoManager")
public class DaoManager {
	@Autowired
	private RollDaoH4 rollDaoH4;
	@Autowired
	private UserDaoH4 userDaoH4;

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

}
