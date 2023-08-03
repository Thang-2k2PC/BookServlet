package com.bookshop.api;

import com.bookshop.dto.ProductDTO;
import com.bookshop.dto.ResponseObject;
import com.bookshop.dto.SearchProductObject;
import com.bookshop.entity.Product;
import com.bookshop.paging.PageRequest;
import com.bookshop.paging.Pageble;
import com.bookshop.service.ProductService;
import com.bookshop.utils.FormUtil;
import com.bookshop.validate.ProductValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/api/product", "/api/product/all", "/api/product/save", "/api/product/delete"})
@MultipartConfig
public class ProductApi extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if (requestURI.equals("/api/product")) {
            String product_id = request.getParameter("product_id");
            if (product_id != null) {
                Product product = productService.getProductById(Long.parseLong(product_id));
                System.out.println();
                mapper.writeValue(response.getOutputStream(), product);
            } else {
                List<Product> latestProducts = productService.getLatestProduct();
                mapper.writeValue(response.getOutputStream(), latestProducts);
            }
        } else {
            if (requestURI.equals("/api/product/all")) {
                Map<String, Object> result = new HashMap<>();
                String pageRequest = request.getParameter("page");
                int page;
                if (pageRequest == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(pageRequest);
                }
                int maxPageItem = 8;
                SearchProductObject searchProductObject = FormUtil.toEntity(SearchProductObject.class, request);
                Pageble pageble = new PageRequest(page, maxPageItem, searchProductObject);
                List<Product> listProduct = productService.getAllProductByFilter(pageble);
                int totalItem = productService.getTotalItemByFilter(pageble);
                int totalPages = (int) Math.ceil((double) totalItem / maxPageItem);
                result.put("listProduct", listProduct);
                result.put("totalPages", totalPages);

                //List<Product> listProduct = productService.getAllProduct();
                mapper.writeValue(response.getOutputStream(), result);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResponseObject responseObject = new ResponseObject();
        ObjectMapper mapper = new ObjectMapper();
        String nameProduct = request.getParameter("nameProduct");
        String product_id = request.getParameter("id");
        String nxb_id = request.getParameter("nxb_id");
        String price = request.getParameter("price");
        String infoProduct = request.getParameter("infoProduct");
        String wareHouse = request.getParameter("wareHouse");
        String category_id = request.getParameter("category_id");
        Part fileImages = request.getPart("images");


        ProductDTO productDTO = new ProductDTO();
        productDTO.setNameProduct(nameProduct);
        productDTO.setInfoProduct(infoProduct);
        productDTO.setPrice(Integer.parseInt(price));
        productDTO.setWareHouse(Integer.parseInt(wareHouse));
        productDTO.setNxb_id(Long.parseLong(nxb_id));
        productDTO.setCategory_id(Long.parseLong(category_id));
        productDTO.setImages(fileImages);

        Map<String, String> valid = ProductValidator.validate(productDTO);
        Product product = new Product();
        //Add book new
        if (product_id == null || product_id.equals("")) {

            if (!valid.isEmpty()) {
                responseObject.setStatus("false");
                responseObject.setErrorMessages(valid);

            } else {
                productService.saveNew(productDTO);
                product = productService.getProductByNameProduct(productDTO.getNameProduct());
//                long id = Long.parseLong(request.getParameter("id"));
                saveImageForProduct(product, productDTO, request);
                responseObject.setStatus("success");
            }
        } else {
            //Update info book
            productDTO.setId(Long.parseLong(product_id));

            productService.saveUpdate(productDTO);
            product.setId(Long.valueOf(product_id));
            saveImageForProduct(product, productDTO, request);
            responseObject.setStatus("success");

        }
        mapper.writeValue(response.getOutputStream(), responseObject);
        System.out.println();

    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResponseObject responseObject = new ResponseObject();
        ObjectMapper mapper = new ObjectMapper();
        String nameProduct = request.getParameter("nameProduct");
        String product_id = request.getParameter("id");
        String nxb_id = request.getParameter("nxb_id");
        String price = request.getParameter("price");
        String infoProduct = request.getParameter("infoProduct");
        String wareHouse = request.getParameter("wareHouse");
        String category_id = request.getParameter("category_id");
        Part fileImages = request.getPart("images");


        ProductDTO productDTO = new ProductDTO();
        productDTO.setNameProduct(nameProduct);
        productDTO.setInfoProduct(infoProduct);
        productDTO.setPrice(Integer.parseInt(price));
        productDTO.setWareHouse(Integer.parseInt(wareHouse));
        productDTO.setNxb_id(Long.parseLong(nxb_id));
        productDTO.setCategory_id(Long.parseLong(category_id));
        productDTO.setImages(fileImages);

        Map<String, String> valid = ProductValidator.validate(productDTO);
        Product product = new Product();
        productDTO.setId(Long.parseLong(product_id));

        productService.saveUpdate(productDTO);
        product.setId(Long.valueOf(product_id));
        saveImageForProduct(product, productDTO, request);
        responseObject.setStatus("success");

        mapper.writeValue(response.getOutputStream(), responseObject);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();

        String product_id = request.getParameter("product_id");
        productService.delete(product_id);
        deleteImageById(product_id);
        responseObject.setStatus("success");
        mapper.writeValue(response.getOutputStream(), responseObject);

    }

    private void saveImageForProduct(Product product, ProductDTO productDTO, HttpServletRequest request) {
        Part productImage = productDTO.getImages();
        String rootDirectory = getServletContext().getInitParameter("rootDirectory");
        Path path = Paths.get(rootDirectory, "/" + product.getId() + ".png");
        if (productImage != null && productImage.getSize() > 0) {
            try {
                productImage.write(path.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Product image saving failed", ex);
            }
        }
    }

    public void deleteImageById(String id) throws IOException {
        String rootDirectory = getServletContext().getInitParameter("rootDirectory");
        String filename = id + ".png";
        Path path = Paths.get(rootDirectory, filename);


        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}
