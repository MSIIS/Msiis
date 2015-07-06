package com.web.service.impl;


import com.web.service.BaserService;
import com.web.service.UserService;
import com.web.soupe.web.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service("userServiceImpl")
@Transactional(readOnly=true)
public class UserServiceImpl extends BaserService implements UserService {


    @Override
    public User findUserByNameAndPassword(String name, String password) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Collection<User> save(Collection<User> users) {
        return null;
    }

    @Override
    public User save(User u) {
        return null;
    }
}
