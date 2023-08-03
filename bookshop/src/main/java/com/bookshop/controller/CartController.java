package com.bookshop.controller;

import com.bookshop.entity.Cart;
import com.bookshop.entity.DetailsCart;
import com.bookshop.entity.Product;
import com.bookshop.entity.User;
import com.bookshop.service.CartService;
import com.bookshop.service.DetailsCartService;
import com.bookshop.service.ProductService;
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
import java.util.*;

@WebServlet("/bookshop/cart")
public class CartController extends HttpServlet {
    @Inject
    private ProductService productService;

    @Inject
    private CartService cartService;

    @Inject
    private DetailsCartService detailsCartService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Long, String> quantity = new HashMap<>();
        List<Product> listProduct = new ArrayList<>();

        User user = (User) SessionUtil.getInstance().getValue(request,"loggedInUser");

        if(user == null) {
            //Lay tu cookie
            Cookie cookie[] = request.getCookies();
            Set<Long> idList = new HashSet<>();
            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getValue().matches("[0-9]+")) {
                    idList.add(Long.valueOf(cookie[i].getName()));
                    quantity.put(Long.valueOf(cookie[i].getName()), cookie[i].getValue());
                }
            }
            if (idList.size() >= 1) {
                listProduct = productService.getAllProductByIdList(idList);
            }
        }else{
            //Lay tu DB
            Cart cart = cartService.getCartByUser(user);
            List<DetailsCart> detailsCart =  detailsCartService.getDetailCartByCart(cart.getId());

            if(detailsCart != null){
                for(DetailsCart detailCart : detailsCart ){
                    listProduct.add(productService.getProductById(detailCart.getProduct_id()));
                    quantity.put(detailCart.getProduct_id(), String.valueOf(detailCart.getQuantity()));
                    System.out.println(quantity.get(detailCart.getProduct_id()));
                }
            }

        }
        request.setAttribute("checkEmpty", listProduct.size());
        request.setAttribute("cart", listProduct);
        request.setAttribute("quantity",quantity);
        RequestDispatcher rd = request.getRequestDispatcher("/client/cart.jsp");
        rd.forward(request, response);
    }
}