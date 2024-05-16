package com.ecommerce.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private record Data(HttpStatus errorCode, String msg, boolean specificMapping) {
        Data(HttpStatus errorCode) {
            this(errorCode, "Not provided.", true);
        }
    }

    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionCustom(ex, request);
    }

    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionCustom(ex, request);
    }

    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionCustom(ex, request);
    }

    public ResponseEntity<Object> handleOthers(Exception ex, WebRequest request) {
        return handleExceptionCustom(ex, request);
    }

    private ResponseEntity<Object> handleExceptionCustom(Exception ex, WebRequest request) {
        Data data = getData(ex);
        if (data.specificMapping()) {
            logger.error("Exception", ex);
        } else {
            logger.error(data.msg(), ex);
        }

        ApiError apiError = new ApiError(data.errorCode(), ex.getMessage() != null ? ex.getMessage() : data.msg(),
                ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(apiError, data.errorCode());
    }

    private Data getData(Exception ex) {
        return switch (ex.getClass().getSimpleName()) {
            case "HttpMessageNotReadableException", "MethodArgumentNotValidException" -> new Data(HttpStatus.BAD_REQUEST);
            case "HttpRequestMethodNotSupportedException" -> new Data(HttpStatus.METHOD_NOT_ALLOWED);
            case "FareNotFoundException" -> new Data(HttpStatus.NOT_FOUND, "Fare not found.", true);
            default -> {
                logger.warn("Exception without specific mapping [{}:{}]. Falling down -> general one.");
                yield new Data(HttpStatus.INTERNAL_SERVER_ERROR, "Exception without specific mapping.", false);
            }
        };
    }
}
