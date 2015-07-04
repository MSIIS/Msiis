package com.web.dao.Hdao;

import com.web.dao.Hdao.base.HBaseDao;
import org.com.soupe.web.User;
import org.springframework.stereotype.Repository;



@Repository("userdaoH4")
public class UserDaoH4 extends HBaseDao<User, Long> {

}