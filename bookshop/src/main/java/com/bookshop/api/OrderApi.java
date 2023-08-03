package com.bookshop.api;

import com.bookshop.dto.ResponseObject;
import com.bookshop.dto.SearchOrderDTO;
import com.bookshop.entity.Order;
import com.bookshop.entity.Product;
import com.bookshop.entity.User;
import com.bookshop.paging.PageOrder;
import com.bookshop.paging.PageOrderRequest;
import com.bookshop.service.OrderService;
import com.bookshop.service.ProductService;
import com.bookshop.service.UserService;
import com.bookshop.utils.FormUtil;
import com.bookshop.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet({"/api/order/all", "/api/order", "/api/order/assign", "/api/order/cancel"})
public class OrderApi extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Inject
    private ProductService productService;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        String path = request.getRequestURI();

        if (path.equals("/api/order/all")) {
            String status = request.getParameter("status");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            int pageRequest = Integer.parseInt(request.getParameter("page"));
            SearchOrderDTO searchOrderDTO = new SearchOrderDTO();
            searchOrderDTO.setStatusOrder(status);
            searchOrderDTO.setFromDate(fromDate);
            searchOrderDTO.setToDate(toDate);
            int page = 0;
            if (pageRequest > 1) {
                page = pageRequest;
            } else {
                page = 1;
            }
            PageOrder pageble = new PageOrderRequest(page, 1, searchOrderDTO);
            List<Order> listOrder = orderService.getAllOrderByFilter(pageble);
            int totailItem = orderService.getTotalItem(pageble);
            int totalPages = (int) Math.ceil((double) totailItem / 1);
            System.out.println();
            responseObject.setTotalPages(totalPages);
            responseObject.setDatas(listOrder);
        }else{
            String order_id = request.getParameter("order_id");
            Order order = orderService.findOrderById(order_id);
            long product_id = order.getProduct_id();
            Product product = productService.getById(product_id);
            User user = userService.getUserById(String.valueOf(order.getUser_id_order()));
            order.setProduct(product);
            order.setUser(user);
            responseObject.setDatas(order);
            System.out.println();
        }
        mapper.writeValue(response.getOutputStream(), responseObject);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        String order_id = request.getParameter("order_id");
        Order order = orderService.findById(order_id);
        order.setId(Long.valueOf(order_id));
        if(path.equals("/api/order/assign")){
            String shipper = request.getParameter("shipper");

            User user = userService.getUserByEmail(shipper);
            order.setStatusOrder("Đang giao");
            order.setShipper_id(user.getId());
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            try {
                Date currentDate = new Date();
                String formatDate = format.format(currentDate);
                Date parsedDate = format.parse(formatDate);
                Timestamp timestamp = new Timestamp(parsedDate.getTime());
                order.setDateDelivery(timestamp);
                orderService.update(order);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else{
            order.setStatusOrder("Đã bị hủy");
            orderService.update(order);
        }

        System.out.println();
    }
}
