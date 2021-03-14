package com.sgveteris.trader.exception;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController implements ErrorController {
	
    @GetMapping("/error")
    public ResponseEntity<Map<String, Object>> errorHandler(HttpServletRequest request, HttpServletResponse response) {
    	final int statusCode = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//    	final String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

    	Exception e = (Exception) request.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR");
    	Map<String, Object> responseMap = new TreeMap<>();
    	if(e != null) {
    		responseMap.put("errorMessage", e.getMessage());
    	}

    	return new ResponseEntity<Map<String, Object>>(
    				responseMap, HttpStatus.valueOf(statusCode));
    }

	public String getErrorPath() {
        return "/error";
    }
	
	
}
