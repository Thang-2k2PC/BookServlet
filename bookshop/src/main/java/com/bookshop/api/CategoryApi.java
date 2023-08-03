package com.bookshop.api;

import com.bookshop.dto.ResponseObject;
import com.bookshop.dto.SearchProductObject;
import com.bookshop.entity.Category;
import com.bookshop.paging.PageRequest;
import com.bookshop.paging.Pageble;
import com.bookshop.service.CategoryService;
import com.bookshop.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = {"/api/categories",  "/api/category/save", "/api/category/update",  "/api/category/delete", "/api/category/all", "/api/category"})
public class CategoryApi extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Inject
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
//        ResponseObject responseObject = new ResponseObject();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String path = request.getRequestURI();
        if(path.equals("/api/categories")){
            List<Category> categories = categoryService.getAllCategory();
            mapper.writeValue(response.getOutputStream(), categories);
        } else if (path.equals("/api/category/all")) {
            int page = Integer.parseInt(request.getParameter("page"));

            SearchProductObject searchProductObject = new SearchProductObject();
            int totalItem = categoryService.getTotalItem();

            int totalPages = (int) Math.ceil((double) totalItem / 6);
            Pageble pageble = new PageRequest(page, 6, searchProductObject);
            List<Category> categories = categoryService.getAllCategoryByPageble(pageble);
            Map<String, Object> result = new HashMap<>();
            result.put("categories", categories);
            result.put("totalPages", totalPages);
            mapper.writeValue(response.getOutputStream(), result);

        }else{
            String category_id = request.getParameter("category_id");
            Category category = categoryService.getCategoryById(category_id);
            mapper.writeValue(response.getOutputStream(), category);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();

        Category categoryNew =  HttpUtil.of(request.getReader()).toEntity(Category.class);

        Map<String, String> errors = new HashMap<>();

        if(categoryNew.getNameCategory().equals("")){
            errors.put("nameCategoryNull","Tên thể loại không được bỏ trống");
            responseObject.setErrorMessages(errors);
            responseObject.setStatus("false");
        }else{
        categoryService.saveCategoryNew(categoryNew);
        responseObject.setStatus("success");
        }
        mapper.writeValue(response.getOutputStream(), responseObject);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        Map<String, String> error = new HashMap<>();
        Category categoryUpdate = HttpUtil.of(request.getReader()).toEntity(Category.class);
        if(categoryUpdate.getNameCategory().equals("")){
            responseObject.setStatus("false");
            error.put("nameCategoryNull", "Tên thể loại không được bỏ trống");
            responseObject.setErrorMessages(error);
        }else{
            categoryService.updateCategory(categoryUpdate);
            responseObject.setStatus("success");

        }
        mapper.writeValue(response.getOutputStream(), responseObject);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        String category_id = request.getParameter("category");
        if(category_id == null){
            responseObject.setStatus("false");
        }else{
//            categoryService.delete(category_id);
            responseObject.setStatus("success");
        }
        mapper.writeValue(response.getOutputStream(), responseObject);

    }
}
