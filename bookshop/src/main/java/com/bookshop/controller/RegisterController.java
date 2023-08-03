package com.bookshop.controller;

import com.bookshop.entity.User;
import com.bookshop.service.UserService;
import com.bookshop.utils.FormUtil;
import com.bookshop.validate.UserValidator;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/bookshop/register")
public class RegisterController extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/client/register.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = FormUtil.toEntity(User.class, request);

        UserValidator userValidator = new UserValidator();
        Map<String, String> valida = userValidator.validate(user, userService);

        if(valida.isEmpty()){
            userService.saveUserNew(user);
            long role_id = user.getRole().getId();
            //Lay id de add vao user_role
            user = userService.getUserByEmail(user.getEmail());
            userService.saveRole_UserNew(user, role_id);
            response.sendRedirect("/bookshop/login");
        }else {
            request.setAttribute("errorValid", valida);
            RequestDispatcher rd = request.getRequestDispatcher("/client/register.jsp");
            rd.forward(request, response);

        }

    }
}
