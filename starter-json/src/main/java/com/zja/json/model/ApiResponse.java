package com.zja.json.model;

import lombok.Data;

/**
 * @Author: zhengja
 * @Date: 2025-04-14 13:57
 */
@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("Success");
        response.setData(data);
        return response;
    }
}