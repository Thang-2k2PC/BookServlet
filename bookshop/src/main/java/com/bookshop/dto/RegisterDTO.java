package com.bookshop.dto;

import javax.servlet.http.HttpServletRequest;

public class RegisterDTO{
    public static void getParam(HttpServletRequest request){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

    }
}
