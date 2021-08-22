package com.guliz.bookstore.security.filter;

import com.guliz.bookstore.security.constants.SecurityConstants;
import com.guliz.bookstore.security.service.BookStoreUserDetailService;
import com.guliz.bookstore.security.service.JwtValidatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(JwtAuthorizationFilter.class);

    private JwtValidatorService jwtValidatorService;
    private BookStoreUserDetailService bookStoreUserDetailService;

    @Autowired
    public JwtAuthorizationFilter(JwtValidatorService jwtValidatorService, BookStoreUserDetailService bookStoreUserDetailService) {
        this.jwtValidatorService = jwtValidatorService;
        this.bookStoreUserDetailService = bookStoreUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        //TODO:
        logger.debug("....passing pre filter chain");

        final String authorizationHeader = httpServletRequest.getHeader(SecurityConstants.AUTHORIZATION);

        if (authorizationHeader == null
                || !authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);

        } else {

            String token = authorizationHeader
                    .replace(SecurityConstants.TOKEN_PREFIX, "");
            String user = jwtValidatorService
                    .extractUsername(token);

            if (user != null) {
                UserDetails userDetails = bookStoreUserDetailService.loadUserByUsername(user);

                if (jwtValidatorService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }

        }
    }
}