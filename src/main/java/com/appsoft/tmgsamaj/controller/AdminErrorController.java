package com.appsoft.tmgsamaj.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class AdminErrorController implements ErrorController {


	    @RequestMapping("/error")
	    public ModelAndView handleError(HttpServletRequest request) {
	        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	        String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

	        if (status != null) {
	            Integer statusCode = Integer.valueOf(status.toString());

	            // Handle 400 errors
	            if (statusCode == HttpStatus.BAD_REQUEST.value() && isProtectedRoute(requestUri)) {
	                return new ModelAndView("404");  // Renders 400.html
	            }

	            // Handle 500 errors
	            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value() && isProtectedRoute(requestUri)) {
	                return new ModelAndView("500");  // Renders 500.html
	            }

	            // Handle 404 errors
	            else if (statusCode == HttpStatus.NOT_FOUND.value() && isProtectedRoute(requestUri)) {
	                return new ModelAndView("404");  // Renders 404.html for missing pages
	            }
	        }

	        // If not a protected route, use the default Spring Boot error handling
	        return null;  
	    }

	    // Check if the request is for a protected route
	    private boolean isProtectedRoute(String requestUri) {
	        return requestUri != null && (requestUri.startsWith("/dashboard") ||
	                                      requestUri.startsWith("/event") ||
	                                      requestUri.startsWith("/donor") ||
	                                      requestUri.startsWith("/committee") ||
	                                      requestUri.startsWith("/edit") ||
	                                      requestUri.startsWith("/update") ||
	                                      requestUri.startsWith("/delete") ||
	                                      requestUri.startsWith("/condolence") ||
	                                      requestUri.startsWith("/donor") ||
	                                      requestUri.startsWith("/view") ||
	                                      requestUri.startsWith("/add") ||
	                                      requestUri.startsWith("/payment") ||
	                                      requestUri.startsWith("/revoke") ||
	                                      requestUri.startsWith("/approve") ||
	                                      requestUri.startsWith("/reject") ||
	                                      requestUri.startsWith("/home") ||
	                                      requestUri.startsWith("/notification") ||

	                                      requestUri.startsWith("/gallery"));
	    }

	  
	}

