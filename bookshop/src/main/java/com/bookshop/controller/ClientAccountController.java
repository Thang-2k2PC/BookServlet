package com.bookshop.controller;

import com.bookshop.dto.ResponseObject;
import com.bookshop.entity.User;
import com.bookshop.service.UserService;
import com.bookshop.utils.PasswordUtils;
import com.bookshop.utils.SessionUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet({"/bookshop/account", "/bookshop/updateInfo", "/bookshop/updatePassword"})
public class ClientAccountController extends HttpServlet {

    @Inject
    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) SessionUtil.getInstance().getValue(request, "loggedInUser");
        request.setAttribute("user", currentUser);
        RequestDispatcher rd = request.getRequestDispatcher("/client/account.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        User currentUser = (User) SessionUtil.getInstance().getValue(request, "loggedInUser");
        ResponseObject ro = new ResponseObject();
        // Lấy dữ liệu JSON từ luồng nhập của request
        BufferedReader reader = request.getReader();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String jsonData = sb.toString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonData);

        if(requestURI.equals("/bookshop/updateInfo")){
            // Lấy giá trị của các thuộc tính từ đối tượng Java
            String username = rootNode.get("username").asText();
            String phone = rootNode.get("phone").asText();
            String address = rootNode.get("address").asText();
            if (username.isEmpty()) {
                ro.setStatus("Tên không được để trống");
            } else if (phone.isEmpty()) {
                ro.setStatus("Số điện thoại không được để trống");
            } else if (address.isEmpty()) {
                ro.setStatus("Địa chỉ không được để trống");
            } else {
            }

            if(username.length() > 1 && phone.length() > 8 && phone.length() < 11 && address.length() > 1){
                currentUser.setUsername(username);
                currentUser.setPhone(phone);
                currentUser.setAddress(address);
                userService.updateUser(currentUser);
            }
        }else{
            String oldPassword = rootNode.get("oldPassword").asText();
            String newPassword = rootNode.get("newPassword").asText();
            String confirmPassword = rootNode.get("confirmPassword").asText();

            boolean matches = PasswordUtils.checkPassword(oldPassword, currentUser.getPassword());
            if(!matches){
                ro.setStatus("Wrong oldPassword ");
            }

            if(!newPassword.equals(confirmPassword)){
                ro.setStatus("PasswordNew != confirmPassword");
            }

            if(matches && newPassword.equals(confirmPassword)){
//                if ()
            }

        }


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        mapper.writeValue(response.getOutputStream(), ro);

    }


}