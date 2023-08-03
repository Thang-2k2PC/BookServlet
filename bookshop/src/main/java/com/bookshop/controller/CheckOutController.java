package com.bookshop.controller;

import com.bookshop.entity.*;
import com.bookshop.service.*;
import com.bookshop.utils.FormUtil;
import com.bookshop.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet({"/bookshop/checkout", "/bookshop/thankyou"})
public class CheckOutController extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private CartService cartService;

    @Inject
    private DetailsCartService detailsCartService;

    @Inject
    private OrderService orderService;

    @Inject
    private DetailsOrderService detailsOrderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) SessionUtil.getInstance().getValue(request,"loggedInUser");
        Map<Long, String> quantity = new HashMap<>();
        List<Product> listProduct = new ArrayList<>();
        Cookie cookie[] = request.getCookies();
        Set<Long> idList = new HashSet<>();

        if(currentUser == null){
            //lay tu cookie (
            for(int i = 0; i < cookie.length; i++){
                if(cookie[i].getName().matches("[0-9]+")){
                    idList.add(Long.parseLong(cookie[i].getName()));
                    quantity.put(Long.parseLong(cookie[i].getName()), cookie[i].getValue());
                }
            }
            listProduct = productService.getAllProductByIdList(idList);


        }else{
            //Lay tu dB
            Cart cart = cartService.getCartByUser(currentUser);
            if(cart != null){
                List<DetailsCart>listDetailCart = detailsCartService.getDetailCartByCart(cart.getId());
                for (DetailsCart detailsCart : listDetailCart) {
                    Product product = productService.getProductById(detailsCart.getProduct_id());
                    listProduct.add(product);
                    quantity.put(detailsCart.getProduct_id(), String.valueOf(detailsCart.getQuantity()));
                }
            }


        }
        request.setAttribute("cart", listProduct);
        request.setAttribute("quantity", quantity);
        request.setAttribute("currentUser", currentUser);

        RequestDispatcher rd = request.getRequestDispatcher("/client/checkout.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Map<Long,String> quanity = new HashMap<Long,String>();
        List<Product> listProduct = new ArrayList<>();
        DetailsOrder detailsOrder = new DetailsOrder();
        User currentUser = (User) SessionUtil.getInstance().getValue(request, "loggedInUser");
        Order order = FormUtil.toEntity(Order.class, request);


        //xu ly dateOrder
        Date systemDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatDate = format.format(systemDate);
        try {
            // Phân tích chuỗi thành đối tượng Date
            Date parsedDate = format.parse(formatDate);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            order.setDateOrder(timestamp);
            if(currentUser != null){
                order.setUser_id_order(currentUser.getId());
            }
            order.setStatusOrder("Đang chờ giao");
            Long order_id = orderService.save(order);
            order.setId(order_id);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        if(currentUser == null){
            Cookie cl[] = request.getCookies();
            Set<Long> idList = new HashSet<Long>();
            for(int i=0; i< cl.length; i++)
            {
                if(cl[i].getName().matches("[0-9]+"))
                {
                    idList.add(Long.parseLong(cl[i].getName()));
                    quanity.put(Long.parseLong(cl[i].getName()), cl[i].getValue());
                }
            }
            listProduct = productService.getAllProductByIdList(idList);

            for(Product product: listProduct){
                detailsOrder.setPrice(product.getPrice());
                detailsOrder.setProduct_id(product.getId());
                detailsOrder.setQuantity_order(order.getQuantity_order());
                detailsOrder.setOrder_id(order.getId());
            }


        }else{
            //luu vao DB



            Cart cart = cartService.getCartByUser(currentUser);
//        List<DetailsCart> listDetailCart = detailsCartService.getDetailCartByCart(cart.getId());
//        for(DetailsCart detailsCart : listDetailCart){
//            DetailsOrder detailsOrder = new DetailsOrder();
//
//            detailsOrder.setProduct(productService.getProductById(order.getProduct_id()));
//            detailsOrder.setQuantity_order();
//        }
//            DetailsOrder detailsOrder = new DetailsOrder();
            detailsOrder.setPrice(order.getPrice());
            detailsOrder.setQuantity_order(order.getQuantity_order());
            detailsOrder.setProduct_id(order.getProduct_id());
            detailsOrder.setOrder_id(order.getId());

//            detailsOrderService.save(detailsOrder);

            Product product = productService.getProductById(order.getProduct_id());
            listProduct.add(product);
            quanity.put(order.getProduct_id(), String.valueOf(order.getQuantity_order()));
        }
        detailsOrderService.save(detailsOrder);
//
//
        cleanUpAfterCheckOut(request,response);
        System.out.println();
        request.setAttribute("order", order);
        request.setAttribute("cart", listProduct);
        request.setAttribute("quantity", quanity);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/client/thankYou.jsp");
        requestDispatcher.forward(request, response);
    }

    private void cleanUpAfterCheckOut(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = (User) SessionUtil.getInstance().getValue(request, "loggedInUser");
        if(currentUser == null){
            Cookie clientCookies[] = request.getCookies();
            for(int i=0;i<clientCookies.length;i++)
            {
                if(clientCookies[i].getName().matches("[0-9]+"))
                {
                    clientCookies[i].setMaxAge(0);
                    clientCookies[i].setPath("/");
                    response.addCookie(clientCookies[i]);
                }
            }
        }else{
            Cart cart = cartService.getCartByUser(currentUser);
            detailsCartService.deleteAllDetailsCarts(cart.getId());

        }

    }
}
