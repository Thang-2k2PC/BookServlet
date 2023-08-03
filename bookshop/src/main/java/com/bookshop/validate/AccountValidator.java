package com.bookshop.validate;

import com.bookshop.dto.AccountDTO;
import com.bookshop.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class AccountValidator {
    public static Map<String, String> validate(AccountDTO dto, UserService userService) {
        Map<String, String> error = new HashMap<>();
        String errorEmail = null;
        String errorConfirmPassword = null;
        if(userService.getUserByEmail(dto.getEmail())  != null){
            errorEmail = "Email đã được đăng ký";
        }
        if(errorEmail != null){
            error.put("email", errorEmail);
        }

        if(!dto.getConfirmPassword().equals(dto.getPassword())){
            errorConfirmPassword = "ConfirmPassword không đúng";
        }
        if(errorConfirmPassword != null){
            error.put("confirmPassword", errorConfirmPassword);
        }

        return error;
    }
}
