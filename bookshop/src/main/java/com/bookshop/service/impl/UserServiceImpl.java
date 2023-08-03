package com.bookshop.service.impl;

import com.bookshop.dao.RoleRepository;
import com.bookshop.dao.UserRepository;
import com.bookshop.entity.Role;
import com.bookshop.entity.User;
import com.bookshop.paging.Pageble;
import com.bookshop.service.UserService;
import com.bookshop.utils.PasswordUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User getUserAndRoleByEmail(String email) {
        return userRepository.findUserAndRoleByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void saveUserNew(User user) {
        user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
        Role role = roleRepository.findRoleByName("ROLE_USER");
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void saveRole_UserNew(User user, Long role_id) {
        userRepository.saveUser_RoleNew(user, role_id);
    }

    @Override
    public User getUserById(String user_id) {
        return userRepository.findUserById(user_id);
    }

    @Override
    public List<User> getAllUserAndRole(Pageble pageble) {
        return userRepository.getAllUserAndRole(pageble);
    }

    @Override
    public Long saveUserForAdmin(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public void deleteUser(String user_id) {
        userRepository.delete(user_id);
    }

    @Override
    public List<User> getUserByRole(String role_shipper) {
        return userRepository.findByRole(role_shipper);
    }

//    @Override
//    public List<User> getUserByRole(Set<Role> role) {
//        return userRepository.findByRole(role);
//    }
}
