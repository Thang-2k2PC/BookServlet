package com.bookshop.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    private static SessionUtil sessionUtil = null;

    //Khoi tao Session neu chua
    public static SessionUtil getInstance() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }



    /**
     *
     *Duy trì thông tin người dùng
     * Lấy session đã tạo request.getSession() sau do put giá trị vào
     */
    public void putValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

   /*
    Lấy thông tin ra: từ Object ta sẽ ép kiểu dữ liệu ta mong muốn
    */
    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    /*
     * Thoát ra khỏi hệ thống
     */
    public void removeValue(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}
