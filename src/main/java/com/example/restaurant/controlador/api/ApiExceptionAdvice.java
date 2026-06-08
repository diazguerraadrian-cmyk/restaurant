package com.example.restaurant.controlador.api;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiExceptionAdvice {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Map<String, Object>> handleError(Exception ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
                response.put("message", "Ocurrió un error.");
                response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(response);

    }
}
