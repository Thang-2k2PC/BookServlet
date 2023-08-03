package com.bookshop.controller.admin;


import com.bookshop.dto.ResponseObject;
import com.bookshop.entity.Category;
import com.bookshop.entity.Nxb;
import com.bookshop.entity.Role;
import com.bookshop.entity.User;
import com.bookshop.service.*;
import com.bookshop.utils.FormUtil;
import com.bookshop.utils.HttpUtil;
import com.bookshop.utils.SessionUtil;
import com.bookshop.validate.UserValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet({"/bookshop/admin/*", })
public class AdminHomeController extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private NxbService nxbService;

    @Inject
    private RoleService roleService;

    @Inject
    private OrderService orderService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        if(path.equals("/bookshop/admin/product")){
            List<Category> listCategory = categoryService.getAllCategory();
            List<Nxb> listNxb = nxbService.getAllNxb();

            request.setAttribute("listCategory", listCategory);
            request.setAttribute("listNxb", listNxb);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/managerProduct.jsp");
            rd.forward(request, response);

        }else if(path.equals("/bookshop/admin/category")){
            RequestDispatcher rd = request.getRequestDispatcher("/admin/managerCategory.jsp");
            rd.forward(request, response);
        }else if(path.equals("/bookshop/admin/nxb")) {
            RequestDispatcher rd = request.getRequestDispatcher("/admin/managerNxb.jsp");
            rd.forward(request, response);
        }else if (path.equals("/bookshop/admin/profile")){
            User user = (User) SessionUtil.getInstance().getValue(request, "loggedInUer");
            request.setAttribute("loggedInUser", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/profile.jsp");
            requestDispatcher.forward(request, response);
        }else if(path.equals("/bookshop/admin/account")){
            List<Role> listRole = roleService.getAllRole();
            request.setAttribute("listRole", listRole);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/managerAccount.jsp");
            rd.forward(request, response);
        }else if(path.equals("/bookshop/admin/order")){
//            Set<Role> role = new HashSet<>();
            // lấy danh sách shipper
//            role.add(roleService.findByName("ROLE_SHIPPER"));
            System.out.println();
            List<User> shippers = userService.getUserByRole("ROLE_SHIPPER");
//            for (User shipper : shippers) {
////                shipper.setListOrder(orderService.findByStatusOrderAndShipper("Đang giao", shipper));
//                shipper.
//            }
            request.setAttribute("allShipper", shippers);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/managerOrder.jsp");
            rd.forward(request, response);
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("/admin/homeAdmin.jsp");
            rd.forward(request, response);

        }
    }

}
