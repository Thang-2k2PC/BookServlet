package com.bookshop.validate;

import com.bookshop.entity.User;
import com.bookshop.service.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserValidator {
    public static Map validate(User user, UserService userService) {
        Map<String, String> errorValid = new HashMap<>();
        String errorPass = null;
        String errorEmail = null;
        String errorConfirmPass = null;

        //Valid Email
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            errorEmail = "Email khong duoc bo trong";

        } else {
            Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
            if (!pattern.matcher(user.getEmail()).matches()) {
                errorEmail = "Email khong dung dinh dang";

            } else {
                //Check email da ton tai chua
                User user1 = userService.getUserByEmail(user.getEmail());
                if (user1 != null) errorEmail = "Email da ton tai";
            }

        }

        //Valid Password
        int length = user.getPassword().length();
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            errorPass = "Password khong duoc de trong";

            //check length tu 8-32
        } else if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errorPass = "Password phai co do dai tu 8-32 ky tu";
        }

        //Vaild ConfirmPassword
        if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            errorConfirmPass = "ConfirmPassword ko dc bo  trong";

        } else if (!user.getConfirmPassword().equals(user.getPassword())) {
            errorConfirmPass = "ConfirmPassword ko khop voi Password";
        }


        if (errorEmail != null) {
            errorValid.put("errorEmail", errorEmail);
        }

        if (errorPass != null) {
            errorValid.put("errorPassword", errorPass);
        }
        if (errorConfirmPass != null) {
            errorValid.put("errorConfirmPassword", errorConfirmPass);
        }
        return errorValid;
    }

    public static Map validate(User user) {
        Map<String, String> errorValid = new HashMap<>();
        String errorUsername = null;
        String errorAddress = null;
        String errorPhone = null;

        if(user.getUsername() == null || user.getUsername().isEmpty()) {
            errorUsername = "Username không được để trống";
            errorValid.put("username" , errorUsername);
        }

        if(user.getAddress() == null || user.getAddress().isEmpty()){
            errorAddress = "Address không được để trống";
            errorValid.put("address", errorAddress);
        }

        if(user.getPhone() == null || user.getPhone().isEmpty()){
            errorPhone = "Phone không được để trống";
        } else if (user.getPhone().length() > 11 || user.getPhone().length() < 9) {
            errorPhone = "Phone tu  9-10 số";
        }else{
            if(errorPhone != null){
                errorValid.put("phone", errorPhone);
            }
        }


        return errorValid;
    }
}
