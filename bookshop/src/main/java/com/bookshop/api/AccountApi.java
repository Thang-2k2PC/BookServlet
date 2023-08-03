package com.bookshop.api;

import com.bookshop.dto.AccountDTO;
import com.bookshop.dto.ResponseObject;
import com.bookshop.dto.SearchProductObject;
import com.bookshop.entity.Role;
import com.bookshop.entity.User;
import com.bookshop.entity.UserRole;
import com.bookshop.paging.PageRequest;
import com.bookshop.paging.Pageble;
import com.bookshop.service.RoleService;
import com.bookshop.service.UserRoleService;
import com.bookshop.service.UserService;
import com.bookshop.utils.HttpUtil;
import com.bookshop.utils.PasswordUtils;
import com.bookshop.validate.AccountValidator;
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

@WebServlet({"/api/account/all", "/api/account/save", "/api/account/delete"})
public class AccountApi extends HttpServlet {

    @Inject
    private RoleService roleService;

    @Inject
    private UserService userService;

    @Inject
    private UserRoleService userRoleService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        String pageRequest = request.getParameter("page");
        String roleNameRequest = request.getParameter("roleName");

        int page = 0;

        if(pageRequest == null){
            page = 1;
        }else{
            page = Integer.parseInt(pageRequest);
        }
        SearchProductObject searchProductObject = new SearchProductObject();
        searchProductObject.setRoleName(roleNameRequest);
        Pageble pageble = new PageRequest(page, 5, searchProductObject);
        List<User> listUserAndRole = userService.getAllUserAndRole(pageble);
        int totalItems = listUserAndRole.get(0).getTotalItems();
        int totalPages = (int) Math.ceil((double) totalItems/5);
        responseObject.setDatas(listUserAndRole);
        responseObject.setTotalPages(totalPages);
        mapper.writeValue(response.getOutputStream(), responseObject);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        AccountDTO dto = HttpUtil.of(request.getReader()).toEntity(AccountDTO.class);
        Map<String, String> validate = AccountValidator.validate(dto, userService);
        if(!validate.isEmpty()){
            responseObject.setErrorMessages(validate);
        }else{
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            String passwordHash = PasswordUtils.hashPassword(dto.getPassword());
            user.setPassword(passwordHash);
            user.setPhone(dto.getPhone());
            user.setAddress(dto.getAddress());
            Long id = userService.saveUserForAdmin(user);
            System.out.println();
            user.setId(id);
            Role role = roleService.findIdByRoleName(dto.getRole_name());
            Long role_id = role.getId();
            userService.saveRole_UserNew(user, role_id);
            System.out.println();
            responseObject.setStatus("success");
        }
        mapper.writeValue(response.getOutputStream(), responseObject);
        System.out.println(dto);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseObject responseObject = new ResponseObject();
        String user_id = request.getParameter("user_id");
        userRoleService.delete(user_id);
        userService.deleteUser(user_id);
        responseObject.setStatus("success");
        mapper.writeValue(response.getOutputStream(), responseObject);
    }
}
