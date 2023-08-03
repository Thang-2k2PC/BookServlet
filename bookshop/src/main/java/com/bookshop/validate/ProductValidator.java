package com.bookshop.validate;

import com.bookshop.dto.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public class ProductValidator {

    public static Map<String, String> validate(ProductDTO productDTO) {
        Map<String, String> errors = new HashMap<>();
        String errorName = null;
        String errorPrice = null;
        String errorWareHouse = null;
        String errorInfoProduct = null;
        String errorImages = null;
        if(productDTO.getNameProduct() == null || productDTO.getNameProduct().isEmpty()){
            errorName = "Tên sản phẩm không được trống";
        }
        if(productDTO.getPrice() < 0){
            errorPrice = " Giá tiền sách không được âm";
        }
        if (productDTO.getWareHouse() < 0) {
            errorWareHouse = "Số lượng sản phẩm không được âm";
        }
        if (productDTO.getInfoProduct() == null || productDTO.getInfoProduct().isEmpty()){
            errorInfoProduct = "Thông tin sách không được để trống";
        }

        if(productDTO.getImages().getSize() ==  0){
            errorImages = "Ảnh sách không được bỏ trống";
        }

        if(errorName != null){
            errors.put("nameProduct", errorName);
        }
        if (errorInfoProduct != null) {
            errors.put("infoProduct", errorInfoProduct);
        }
        if (errorPrice != null) {
            errors.put("price", errorPrice);
        }
        if (errorWareHouse != null) {
            errors.put("wareHouse", errorWareHouse);
        }
        if(errorImages != null){
            errors.put("images", errorImages);
        }

        return errors;
    }
}
