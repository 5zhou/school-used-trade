package com.zouwu.trade.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private int code;

    private String message;

    private T data;


    public static ApiResponse<String> defaultSuccessResponse(){
        return new ApiResponse<>(0, "success", null);
    }

    public static ApiResponse<String> defaultFailResponse(String msg){
        return new ApiResponse<>(500, msg, null);
    }

}
