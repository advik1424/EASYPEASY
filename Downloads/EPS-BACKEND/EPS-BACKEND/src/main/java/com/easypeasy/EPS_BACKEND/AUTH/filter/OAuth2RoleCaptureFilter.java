package com.easypeasy.EPS_BACKEND.AUTH.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class OAuth2RoleCaptureFilter extends OncePerRequestFilter {

    public static final String OAUTH2_ROLE_SESSION_ATTR = "OAUTH2_ROLE";
    public static final String OAUTH2_REDIRECT_URI_SESSION_ATTR = "OAUTH2_REDIRECT_URI";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/oauth2/authorization")) {
            String role = request.getParameter("role");
            String redirectUri = request.getParameter("redirect_uri");

            if ((role != null && !role.isBlank()) || (redirectUri != null && !redirectUri.isBlank())) {
                HttpSession session = request.getSession(true);

                if (role != null && !role.isBlank()) {
                    session.setAttribute(OAUTH2_ROLE_SESSION_ATTR, role.trim().toUpperCase());
                }
                if (redirectUri != null && !redirectUri.isBlank()) {
                    session.setAttribute(OAUTH2_REDIRECT_URI_SESSION_ATTR, redirectUri.trim());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
