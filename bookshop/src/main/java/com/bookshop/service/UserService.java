package com.bookshop.service;

import com.bookshop.entity.Role;
import com.bookshop.entity.User;
import com.bookshop.paging.Pageble;

import java.util.List;
import java.util.Set;


public interface UserService {
    User getUserByEmail(String email);

    User getUserAndRoleByEmail(String email);

    void updateUser(User user);

     void saveUserNew(User user);

     void saveRole_UserNew(User user, Long role_id);

    User getUserById(String user_id);

    List<User> getAllUserAndRole(Pageble pageble);

    Long saveUserForAdmin(User user);

    void deleteUser(String user_id);

    List<User> getUserByRole(String role_shipper);

}
