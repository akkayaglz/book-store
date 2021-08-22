package com.guliz.bookstore.security.interceptor;

import com.guliz.bookstore.security.model.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.guliz.bookstore.security.constants.SecurityConstants.AUTHORIZATION;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final SecurityContext context;

    @Autowired
    public AuthorizationInterceptor(SecurityContext context) {
        this.context = context;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        context.setToken(Optional.ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(null));
        return true;
    }

}
