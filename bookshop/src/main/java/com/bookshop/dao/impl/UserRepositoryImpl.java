package com.bookshop.dao.impl;

import com.bookshop.dao.UserRepository;
import com.bookshop.entity.Role;
import com.bookshop.entity.User;
import com.bookshop.mapper.UserAndRoleMapper;
import com.bookshop.mapper.UserAndRole_TotalItemsMapper;
import com.bookshop.mapper.UserMapper;
import com.bookshop.paging.Pageble;

import java.util.List;
import java.util.Set;

public class UserRepositoryImpl extends AbstractDAO<User> implements UserRepository {

    @Override
    public User findUserByEmail(String email) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users ");
        sql.append(" WHERE email = '" + email + "'");
        List<User> users = query(sql.toString(), new UserMapper());
        return users.isEmpty()?null : users.get(0);
    }

    @Override
    public void updateUser(User user) {
        StringBuilder sql = new StringBuilder("UPDATE users");
        sql.append(" SET username = ?" + " , address = ? " + " , phone = ?");
        sql.append(" WHERE  id = ?");
        update(sql.toString(), user.getUsername(), user.getAddress(), user.getPhone(),user.getId());
    }

    @Override
    public void save(User user) {
        StringBuilder sql = new StringBuilder("INSERT INTO users(email, password, username, address, phone) ");
        sql.append(" VALUES(?,?,?,?,?)");
        insert(sql.toString(), user.getEmail(), user.getPassword(), user.getUsername(), user.getAddress(), user.getPhone());

    }

    @Override
    public void saveUser_RoleNew(User user, Long role_id) {
        StringBuilder sql = new StringBuilder(" INSERT INTO user_role(user_id, role_id) ");
        sql.append(" VALUES(?,?)");
        insert(sql.toString(), user.getId(), role_id);
    }

    @Override
    public User findUserAndRoleByEmail(String email) {
        StringBuilder sql = new StringBuilder("SELECT u.id, address, email, username, password, phone, role_id, name");
        sql.append(" FROM users   u");
        sql.append(" INNER JOIN user_role AS ur ON u.id = ur.user_id");
        sql.append(" INNER JOIN roles AS r ON r.id = ur.role_id");
        sql.append(" WHERE email = ?");
        List<User> userList = query(sql.toString(), new UserAndRoleMapper(), email);
        return userList.isEmpty()? null : userList.get(0);
    }

    @Override
    public User findUserById(String user_id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users ");
        sql.append(" WHERE id = ?");
        List<User> users = query(sql.toString(), new UserMapper(), user_id);
        return users.isEmpty()? null: users.get(0);
    }

    @Override
    public List<User> getAllUserAndRole(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT u.id,email, username, password, phone, address, r.id AS role_id, name, COUNT(*) OVER() AS totalItems ");
        sql.append(" FROM users AS u ");
        sql.append(" INNER JOIN user_role AS ur ON ur.user_id = u.id  ");
        sql.append(" INNER JOIN roles AS r ON ur.role_id = r.id");
        sql.append(" WHERE name = ?");
        if(pageble.getOffset() != null && pageble.getLimit() != null){
            sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");//Lay tu vi tri n-> m
        }

        return query(sql.toString(), new UserAndRole_TotalItemsMapper(), pageble.getSearchProduct().getRoleName());
    }

    @Override
    public Long saveUser(User user) {
        StringBuilder sql = new StringBuilder("INSERT INTO users(email, password, username, address, phone) ");
        sql.append(" VALUES(?,?,?,?,?)");
        return insert(sql.toString(), user.getEmail(), user.getPassword(), user.getUsername(), user.getAddress(), user.getPhone());

    }

    @Override
    public void delete(String user_id) {
        StringBuilder sql = new StringBuilder(" DELETE FROM users ");
        sql.append(" WHERE id = ?");
        update(sql.toString(), user_id);
    }

    @Override
    public List<User> findByRole(String role_shipper) {
        StringBuilder sql = new StringBuilder("SELECT u.id, email, username, password, phone, address, r.id AS role_id, name");
        sql.append(" FROM users AS u ");
        sql.append(" INNER JOIN user_role AS ur ON ur.user_id = u.id ");
        sql.append(" INNER JOIN roles AS r ON ur.role_id = r.id ");
        sql.append(" WHERE name = ?");

        return query(sql.toString(), new UserAndRoleMapper(), role_shipper);
    }

//    @Override
//    public List<User> findByRole(Set<Role> role) {
//        StringBuilder sql = new StringBuilder("SELECT u.id, email, username, password, phone, address, r.id AS role_id, name");
//        sql.append(" FROM users AS u ");
//        sql.append(" INNER JOIN user_role AS ur ON ur.user_id = u.id ");
//        sql.append(" INNER JOIN roles AS r ON ur.role_id = r.id ");
//        sql.append(" WHERE role_id = ?");
//
//        return query(sql.toString(), new UserAndRoleMapper(), role.);
//    }
}
