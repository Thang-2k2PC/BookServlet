package com.bookshop.filter;

import com.bookshop.entity.User;
import com.bookshop.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        java.lang.String url = request.getRequestURI();
        if(url.startsWith("/bookshop/admin")){
            User user = (User) SessionUtil.getInstance().getValue(request,"loggedInUser" );
            if(user != null){
                if(user.getRole().getName().equals("ROLE_ADMIN")){
                    filterChain.doFilter(request, response);
                } else if (user.getRole().getName().equals("ROLE_USER")) {
                    response.sendRedirect(request.getContextPath()+"/bookshop/login?accessDenied=accessDenied");
                }
            }else{
                response.sendRedirect(request.getContextPath()+"/bookshop/login?notLogin=not_login");
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
      }

    @Override
    public void destroy() {

    }
}
