package com.FlyNova.payload.response;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ApiResponse {
    private String message;


    public ApiResponse() {
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
