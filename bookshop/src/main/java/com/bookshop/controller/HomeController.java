package com.bookshop.controller;


import com.bookshop.entity.User;
import com.bookshop.service.UserService;
import com.bookshop.utils.PasswordUtils;
import com.bookshop.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/bookshop", "/bookshop/login", "/bookshop/logout"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

//    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String invaldParam = request.getParameter("invalid");
        String logoutParam = request.getParameter("logout");
        String accessDeniedParam = request.getParameter("accessDenied");
        String notLoginParam = request.getParameter("notLogin");
        Map<String, String> messageError = new HashMap<>();
        if (invaldParam != null) {
//            String message = resourceBundle.getString(messageParam);
            messageError.put("invaild", invaldParam);
        }
        if (logoutParam != null) {
//            String logout = resourceBundle.getString(logoutParam);
            messageError.put("logout", logoutParam);
        }
        if(accessDeniedParam != null){
            messageError.put("accessDenied", accessDeniedParam);
        }
        if(notLoginParam != null){
            messageError.put("notLogin", notLoginParam);
        }
        request.setAttribute("messageError", messageError);

        //Lay thong tin user dang nhap
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (requestURI.equals("/bookshop/login")) {
            RequestDispatcher rd = request.getRequestDispatcher("/client/login.jsp");
            rd.forward(request, response);
        } else if (requestURI.equals("/bookshop/logout")) {
            SessionUtil.getInstance().removeValue(request, "loggedInUser");
            response.sendRedirect(request.getContextPath() + "/bookshop/login?logout=logout");
        } else {
            request.setAttribute("loggedInUser", loggedInUser);
            RequestDispatcher rd = request.getRequestDispatcher("/client/home.jsp");
            rd.forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String passwordRequest = request.getParameter("password");

        User user = userService.getUserAndRoleByEmail(email);
        if (user != null) {
            boolean matches = PasswordUtils.checkPassword(passwordRequest, user.getPassword());
            if (matches) {
                SessionUtil.getInstance().putValue(request, "loggedInUser", user);
                String role = user.getRole().getName();
//                boolean check = user.getRole().getName().equals();
                if (user.getRole().getName().equals("ROLE_ADMIN")) {
                    String path = request.getContextPath();
                    response.sendRedirect(request.getContextPath() + "/bookshop/admin");
                } else if (user.getRole().getName().equals("ROLE_USER")) {
                    response.sendRedirect(request.getContextPath() + "/bookshop");

                }
            } else {
                response.sendRedirect(request.getContextPath() + "/bookshop/login?invalid=username_password_invalid");

            }
        }

    }
}


