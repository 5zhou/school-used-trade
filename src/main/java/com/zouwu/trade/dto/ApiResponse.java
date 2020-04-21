package com.zouwu.trade.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private int code;

    private String message;

    private T data;

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(0, "success", null);
    }

    public static ApiResponse<Void> success(String msg) {
        return new ApiResponse<>(0, msg, null);
    }

    public static ApiResponse<Void> fail(String msg) {
        return new ApiResponse<>(500, msg, null);
    }

    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(0, msg, data);
    }


    public static void main(String[] args) {
        System.out.println(gcd(7, 24));
    }

    private static int gcd(int a, int b) {
        Assert.isTrue(a > 0 && b > 0, "hahah");
        if (a < b) {
            return gcd(b, a);
        }
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a - b);
    }

}
