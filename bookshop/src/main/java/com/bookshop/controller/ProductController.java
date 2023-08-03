package com.bookshop.controller;

import com.bookshop.entity.Product;
import com.bookshop.service.ProductService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookshop/product")
public class ProductController extends HttpServlet {
    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id != null){
            Product product = productService.getProductById(Long.parseLong(id));
            request.setAttribute("product", product);
            RequestDispatcher rd = request.getRequestDispatcher("/client/detailProduct.jsp");
            rd.forward(request, response);
        }
    }
}
