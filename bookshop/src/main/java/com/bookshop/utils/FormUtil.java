package com.bookshop.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil {
    /*Thư viện giúp convert các field từ form của tầng view sang model chú ý cần đặt tên field trùng với thuộc tính trong model*/
    @SuppressWarnings("unchecked")
    public static <T> T toEntity(Class<T> clazz, HttpServletRequest request)
    {
        T object=null;
        try {
            object=clazz.newInstance();
            BeanUtils.populate(object, request.getParameterMap());

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e ) {
            System.out.print(e.getMessage());
        }
        return object;
    }
}
