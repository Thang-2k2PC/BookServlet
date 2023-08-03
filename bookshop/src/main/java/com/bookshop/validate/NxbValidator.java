package com.bookshop.validate;

import java.util.HashMap;
import java.util.Map;

public class NxbValidator {
    public static Map<String, String> validate(String nameNxb) {
        Map<String, String> errors = new HashMap<>();
        String valueError = null;
        if (nameNxb == null) {
            valueError = "Tên Nhà Xuất Bản Không Được Để Trống";
            errors.put("nameNxb", valueError);
        }
        return errors;
    }
}
