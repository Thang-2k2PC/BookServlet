package com.bookshop.utils;

import com.bookshop.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordUtils {

    public static String hashPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        //ma hoa password
        String hashPassword = passwordEncoder.encode(password);
        return hashPassword;
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Kiểm tra mật khẩu với hashed password
        boolean matched = passwordEncoder.matches(password, hashedPassword);

        return matched;
    }
}
