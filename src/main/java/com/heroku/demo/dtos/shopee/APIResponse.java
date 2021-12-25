package com.heroku.demo.dtos.shopee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse<T> {
    private Integer status;
    private String message;
    private Boolean error;
    private T data;

    public static APIResponse of(Object data, Integer status){
        APIResponse build = APIResponse.builder()
                .data(data)
                .status(status)
                .build();
        return build;
    }

    public static APIResponse of(Object data){
        APIResponse build = APIResponse.builder()
                .data(data)
                .build();
        return build;
    }


    public static APIResponse of(Object data, Integer status, String message){
        APIResponse build = APIResponse.builder()
                .data(data)
                .status(status)
                .message(message)
                .build();
        return build;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", error=" + error +
                ", data=" + data +
                '}';
    }
}
