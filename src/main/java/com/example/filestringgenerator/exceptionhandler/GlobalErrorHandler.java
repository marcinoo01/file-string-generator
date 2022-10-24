package com.example.filestringgenerator.exceptionhandler;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalErrorHandler extends DefaultErrorAttributes {

    private static final String PAYLOAD_IS_INVALID = "Provided payload is invalid";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("message", PAYLOAD_IS_INVALID);
        return map;
    }
}
