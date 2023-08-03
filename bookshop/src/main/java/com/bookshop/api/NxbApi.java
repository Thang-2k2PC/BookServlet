package com.bookshop.api;

import com.bookshop.dto.ResponseObject;
import com.bookshop.dto.SearchProductObject;
import com.bookshop.entity.Category;
import com.bookshop.entity.Nxb;
import com.bookshop.paging.PageRequest;
import com.bookshop.paging.Pageble;
import com.bookshop.service.NxbService;
import com.bookshop.utils.FormUtil;
import com.bookshop.utils.HttpUtil;
import com.bookshop.validate.NxbValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet({"/api/nxb/all", "/api/nxb/save","/api/nxb/update", "/api/nxb", "/api/nxb/delete"})
public class NxbApi extends HttpServlet {

    @Inject
    private NxbService nxbService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();

        String nxb_id = request.getParameter("nxb_id");
        if(nxb_id == null){

            String pageRequest = request.getParameter("page");

            int page = Integer.parseInt(pageRequest);
            int totalItems  = 0;
            int totalPages = 0;
            SearchProductObject searchProductObject = new SearchProductObject();
            totalItems = nxbService.getTotalItem();
            totalPages = (int) Math.ceil((double) totalItems/5);

            Pageble pageble = new PageRequest(page, 5, searchProductObject);
            List<Nxb> nxbs = nxbService.getAllByPageble(pageble);

            responseObject.setTotalPages(totalPages);
            responseObject.setDatas(nxbs);
        }else{
            Nxb nxb = nxbService.findNxbById(Long.parseLong(nxb_id));
            responseObject.setDatas(nxb);
        }

        mapper.writeValue(response.getOutputStream(), responseObject);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();

        Nxb nxb = HttpUtil.of(request.getReader()).toEntity(Nxb.class);
        String nameNxb = nxb.getNameNxb();
        Map<String,String> errors = NxbValidator.validate(nameNxb);
        if(errors.size() > 0){
            responseObject.setStatus("false");
            responseObject.setErrorMessages(errors);
        }else{
            nxbService.saveNxbNew(nameNxb);
            responseObject.setStatus("success");
        }
        mapper.writeValue(response.getOutputStream(), responseObject);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResponseObject responseObject = new ResponseObject();
        ObjectMapper mapper = new ObjectMapper();
        Nxb nxb = HttpUtil.of(request.getReader()).toEntity(Nxb.class);
        if(nxb == null){
            responseObject.setStatus("false");
        }else{
            nxbService.updateNxb(nxb);
            responseObject.setStatus("success");

        }
        mapper.writeValue(response.getOutputStream(), responseObject);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        Long nxb_id = Long.valueOf(request.getParameter("nxb_id"));
        nxbService.delete(nxb_id);
        responseObject.setStatus("success");
        mapper.writeValue(response.getOutputStream(), responseObject);
    }
}
