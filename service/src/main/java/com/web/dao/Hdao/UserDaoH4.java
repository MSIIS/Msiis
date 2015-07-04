package com.web.dao.Hdao;

import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Component("userDaoH4")
public class UserDaoH4 extends HBaseDao<User, Long> {

}