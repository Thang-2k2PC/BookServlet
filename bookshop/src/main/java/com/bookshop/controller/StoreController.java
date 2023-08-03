package com.bookshop.controller;

import com.bookshop.dto.SearchProductObject;
import com.bookshop.entity.Category;
import com.bookshop.entity.Nxb;
import com.bookshop.entity.Product;
import com.bookshop.paging.PageRequest;
import com.bookshop.paging.Pageble;
import com.bookshop.service.CategoryService;
import com.bookshop.service.NxbService;
import com.bookshop.service.ProductService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/bookshop/store")
public class StoreController extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private NxbService nxbService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRequest = request.getParameter("page");
        String priceRequest = request.getParameter("price");
        String nxbRequest = request.getParameter("nxb");

        int page = 0;
        String price = null;
        String nxb_id = null;

        if(pageRequest == null){
            page = 1;
        }else {
            page = Integer.parseInt(pageRequest);
        }

        if(priceRequest != null && !priceRequest.equals("")){
            price = priceRequest;
        }
//        
        if(nxbRequest != null && !nxbRequest.equals("")){
//            String nxb = request.getParameter("nxbRequest");
            Nxb nxbs = nxbService.getNxb_idByNameNxb(nxbRequest);
            nxb_id = String.valueOf(nxbs.getId());
        }

        //Lay nameCategory-> tim id cua category
        String nameCategory = request.getParameter("category");
        Category category = categoryService.findCategoryByNameCategory(nameCategory);
        long category_id = category.getId();


        SearchProductObject searchProductObject= new SearchProductObject();
        searchProductObject.setCategory_id(category_id);
        searchProductObject.setPrice(price);
        searchProductObject.setNxb_id(nxb_id);


        int maxPageItem = 12;
        int totalItem = 0;
        int totalPage = 0;

        Pageble pageble = new PageRequest(page,12, searchProductObject);
        //Lay all product cua category do
        List<Product> listProduct = productService.getAllProductPageByCategoryId(pageble);

        // count tong so product
        totalItem = productService.getTotalItemByPageble(pageble);

        totalPage = (int) Math.ceil((double) totalItem / maxPageItem);
        List<Integer> pagelist = new ArrayList<Integer>();

        //Lap ra danh sach cac trang
        if(page==1 || page ==2 || page == 3 || page == 4)
        {
            for(int i = 2; i <=5 && i<=totalPage; i++)
            {
                pagelist.add(i);
            }
        }else if(page == totalPage)
        {
            for(int i = totalPage; i >= totalPage - 3 && i> 1; i--)
            {
                pagelist.add(i);
            }
            Collections.sort(pagelist);
        }else
        {
            for(int i = page; i <= page + 2 && i<= totalPage; i++)
            {
                pagelist.add(i);
            }
            for(int i = page-1; i >= page - 2 && i> 1; i--)
            {
                pagelist.add(i);
            }
            Collections.sort(pagelist);
        }

        //Lay ra cac category va nxb
        List<Product> products = productService.getProductAndNxbByCategoryId(category_id);
        Set<String> nxb = new HashSet<String>();
        for(Product product: products)
        {
            nxb.add(product.getNxb().getNameNxb());
        }

        request.setAttribute("list", listProduct);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageList", pagelist);
        request.setAttribute("nxbs", nxb);
        request.setAttribute("category", category);
        RequestDispatcher rd = request.getRequestDispatcher("/client/store.jsp");
        rd.forward(request, response);
    }
}
