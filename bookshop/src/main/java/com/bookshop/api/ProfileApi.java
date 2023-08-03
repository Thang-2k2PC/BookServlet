package com.bookshop.api;

import com.bookshop.dto.ResponseObject;
import com.bookshop.entity.User;
import com.bookshop.service.UserService;
import com.bookshop.utils.FormUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/api/profile/user", "/api/profile/update", "/api/"})
public class ProfileApi extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        String user_id = request.getParameter("user_id");
        User user = userService.getUserById(user_id);
        responseObject.setDatas(user);
        mapper.writeValue(response.getOutputStream(), responseObject);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = FormUtil.toEntity(User.class, request);


    }
}
