package com.web.dao.Hdao;

import com.util.model.PageInfo;
import com.web.dao.Hdao.base.HBaseDao;
import com.web.soupe.web.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Component("userDaoH4")
public class UserDaoH4 extends HBaseDao<User, Long> {

    @Override
    public void insertBatchH(Collection<?> collection) {
    }
}