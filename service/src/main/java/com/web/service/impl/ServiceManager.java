package com.web.service.impl;

import com.web.service.SsqService;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("serviceManager")
public class ServiceManager{
	@Autowired
	private SsqService ssqService ;
	@Autowired
	private UserService userService;
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
	
	

}
