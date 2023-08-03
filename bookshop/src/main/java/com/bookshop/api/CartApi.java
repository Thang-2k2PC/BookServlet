package com.bookshop.api;

import com.bookshop.dto.ResponseObject;
import com.bookshop.entity.Cart;
import com.bookshop.entity.DetailsCart;
import com.bookshop.entity.Product;
import com.bookshop.entity.User;
import com.bookshop.service.CartService;
import com.bookshop.service.DetailsCartService;
import com.bookshop.service.ProductService;
import com.bookshop.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/cart/*")
public class CartApi extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private CartService cartService;

    @Inject
    private DetailsCartService detailsCartService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String id = request.getParameter("id");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject ro = new ResponseObject();
        Product product = productService.getProductById(Long.parseLong(id));

        Cookie cookie[] = request.getCookies();
        User user = (User) SessionUtil.getInstance().getValue(request, "loggedInUser");

        //Add product vao cart
        if (requestURI.equals("/api/cart/addProduct")) {
            //Neu product het-> ko luu
            if (product.getWareHouse() == 0) {
                ro.setStatus("false");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                mapper.writeValue(response.getOutputStream(), ro);
            }
            if (user == null) {
                //Su dung cookie de luu
                boolean found = false;
                for (int i = 0; i < cookie.length; i++) {
                    if (cookie[i].getName().equals(id)) {   //Neu product da co trong cookie -> tang slg 1
                        String value = Integer.toString(Integer.parseInt(cookie[i].getValue()) + 1);
                        cookie[i].setValue(Integer.toString(Integer.parseInt(cookie[i].getValue()) + 1));
                        cookie[i].setMaxAge(60 * 60 * 24 * 7);
                        cookie[i].setPath("/");
                        response.addCookie(cookie[i]);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    //Neu product ko co trong cookie
                    Cookie cookie1 = new Cookie(id, "1");
                    cookie1.setMaxAge(60 * 60 * 24 * 7);
                    cookie1.setPath("/");
                    response.addCookie(cookie1);
                }
            } else {
                //Luu bang DB
                Cart cart = cartService.getCartByUser(user);
                if (cart == null) {
                    cart = new Cart();
                    cart.setUser_id(user.getId());
                    cartService.save(cart);
                }

                DetailsCart detailsCart = detailsCartService.getDetailCartByProductAndCart(product.getId(), cart.getId());
                if (detailsCart == null) {//Neu ko tim thay chi tiet gio hang -> tao moi
                    detailsCart = new DetailsCart();
                    detailsCart.setCart_id(cart.getId());
                    detailsCart.setProduct_id(product.getId());
                    detailsCart.setQuantity(1);
                    detailsCartService.save(detailsCart);

                } else {
                    //Neu product da co trong DB tang so luong them 1
                    detailsCart.setQuantity(detailsCart.getQuantity() + 1);
                    detailsCartService.updateDetailsCart(detailsCart);
                }

            }
            //Api changProductQuantity
        } else if (requestURI.equals("/api/cart/changeProductQuanity")) {
            String value = request.getParameter("value");
            if (user == null) {
                for (int i = 0; i < cookie.length; i++) {
                    if (cookie[i].getName().equals(id)) {
                        cookie[i].setValue(value);
                        cookie[i].setPath("/");
                        cookie[i].setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(cookie[i]);
                        break;
                    }
                }
            } else {
                Cart cart = cartService.getCartByUser(user);
                DetailsCart detailsCart = detailsCartService.getDetailCartByProductAndCart(product.getId(), cart.getId());
                detailsCart.setQuantity(Integer.parseInt(value));
                detailsCartService.updateDetailsCart(detailsCart);
            }
        } else {
            if (user == null) {
                //Xoa product tu cookie
                for (int i = 0; i < cookie.length; i++) {
                    if (cookie[i].getName().equals(id)) {
                        cookie[i].setMaxAge(0);
                        System.out.println(cookie[i].getMaxAge());
                        cookie[i].setPath("/");
                        response.addCookie(cookie[i]);
                        break;
                    }
                }
            }else{
                Cart cart = cartService.getCartByUser(user);
                DetailsCart detailsCart = detailsCartService.getDetailCartByProductAndCart(product.getId(), cart.getId());
                detailsCartService.deleteDetailsCarts(detailsCart);
            }
        }
        ro.setStatus("success");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        mapper.writeValue(response.getOutputStream(), ro);
    }
}
