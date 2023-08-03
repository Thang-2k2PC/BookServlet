package com.bookshop.service.impl;

import com.bookshop.dao.UserRoleRepository;
import com.bookshop.service.UserRoleService;

import javax.inject.Inject;

public class UserRoleServiceImpl implements UserRoleService {

    @Inject
    private UserRoleRepository userRoleRepository;

    @Override
    public void delete(String user_id) {
        userRoleRepository.delete(user_id);
    }
}
