package com.ecommerce.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class ApiError {
    private HttpStatus status;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

}
