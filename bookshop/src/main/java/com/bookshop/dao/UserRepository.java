package com.bookshop.dao;

import com.bookshop.entity.Role;
import com.bookshop.entity.User;
import com.bookshop.paging.Pageble;

import java.util.List;
import java.util.Set;

public interface UserRepository extends GenericDAO<User> {
    User findUserByEmail(String email);

    void updateUser(User user);

    void save(User user);

    void saveUser_RoleNew(User user, Long role_id);

    User findUserAndRoleByEmail(String email);

    User findUserById(String user_id);

    List<User> getAllUserAndRole(Pageble pageble);

    Long saveUser(User user);

    void delete(String user_id);

    List<User> findByRole(String role_shipper);

}
