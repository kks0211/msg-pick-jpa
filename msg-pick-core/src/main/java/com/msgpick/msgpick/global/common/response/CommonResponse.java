package com.msgpick.msgpick.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private boolean success;
    private T data;
    private String message;
    private String errorCode;

    public static <T> CommonResponse<T> success(T data, String message) {
        return (CommonResponse<T>) CommonResponse.builder()
                .success(true)
                .data(data)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> success(T data) {
        return success(data, null);
    }

    public static CommonResponse fail(String message, String errorCode) {
        return CommonResponse.builder()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .build();
    }

    public static CommonResponse fail(ErrorCode errorCode) {
        return CommonResponse.builder()
                .success(false)
                .message(errorCode.getErrorMsg())
                .errorCode(errorCode.name())
                .build();
    }

}
