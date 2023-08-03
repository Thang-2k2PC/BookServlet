package com.bookshop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {
	
	private String value;
	
	public HttpUtil (String value) {
		this.value = value;
	}

//	chuyển đổi chuỗi dữ liệu JSON được lưu trữ trong biến value thành đối tượng Java tương ứng
	public <T> T toEntity(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}

//	sử dụng để đọc dữ liệu từ yêu cầu HTTP bằng đối tượng BufferedReader, sau đó chuyển đổi chuỗi dữ liệu đọc được thành đối tượng Java
	public static HttpUtil of (BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line);
		    }
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
