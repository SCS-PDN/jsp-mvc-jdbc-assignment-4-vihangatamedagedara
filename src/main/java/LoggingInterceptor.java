package com.university.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String uri = request.getRequestURI();
        String method = request.getMethod();

        System.out.println("User Action: " + method + " " + uri);

        if (uri.contains("/login")) {
            System.out.println("Login attempt detected");
        }

        if (uri.contains("/register")) {
            System.out.println("Course registration attempt detected");
        }

        return true;
    }
}