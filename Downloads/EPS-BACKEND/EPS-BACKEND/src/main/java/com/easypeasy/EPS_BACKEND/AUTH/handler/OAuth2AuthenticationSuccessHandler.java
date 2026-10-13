package com.easypeasy.EPS_BACKEND.AUTH.handler;

import com.easypeasy.EPS_BACKEND.AUTH.filter.OAuth2RoleCaptureFilter;
import com.easypeasy.EPS_BACKEND.AUTH.service.JwtService;
import com.easypeasy.EPS_BACKEND.USERS.entity.Role;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import com.easypeasy.EPS_BACKEND.USERS.reposistory.UserReposistory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.UUID;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserReposistory userReposistory;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public OAuth2AuthenticationSuccessHandler(
            UserReposistory userReposistory,
            JwtService jwtService,
            @Lazy PasswordEncoder passwordEncoder) {
        this.userReposistory = userReposistory;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (email == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not provided by Google account");
            return;
        }

        HttpSession session = request.getSession(false);
        String requestedRole = null;
        String redirectUri = null;

        if (session != null) {
            requestedRole = (String) session.getAttribute(OAuth2RoleCaptureFilter.OAUTH2_ROLE_SESSION_ATTR);
            redirectUri = (String) session.getAttribute(OAuth2RoleCaptureFilter.OAUTH2_REDIRECT_URI_SESSION_ATTR);
            session.removeAttribute(OAuth2RoleCaptureFilter.OAUTH2_ROLE_SESSION_ATTR);
            session.removeAttribute(OAuth2RoleCaptureFilter.OAUTH2_REDIRECT_URI_SESSION_ATTR);
        }

        // Find existing user or register new one
        final String roleToAssign = requestedRole;
        User user = userReposistory.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name != null ? name : "Google User");
            newUser.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));

            if ("SHOPKEEPER".equalsIgnoreCase(roleToAssign)) {
                newUser.setRole(Role.SHOPKEEPER);
            } else {
                newUser.setRole(Role.STUDENT);
            }

            return userReposistory.save(newUser);
        });

        // Generate JWT token using existing JwtService
        String jwtToken = jwtService.generateToken(user);

        // If redirect_uri is specified, redirect to frontend with token details
        if (redirectUri != null && !redirectUri.isBlank()) {
            String targetUrl = UriComponentsBuilder.fromUriString(redirectUri)
                    .queryParam("token", jwtToken)
                    .queryParam("email", user.getEmail())
                    .queryParam("role", user.getRole().name())
                    .queryParam("name", user.getName())
                    .build()
                    .toUriString();

            response.sendRedirect(targetUrl);
            return;
        }

        // Otherwise, write JSON response directly
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String jsonResponse = String.format(
                "{\"token\":\"%s\",\"email\":\"%s\",\"role\":\"%s\",\"name\":\"%s\",\"message\":\"Google login successful\"}",
                escapeJson(jwtToken),
                escapeJson(user.getEmail()),
                escapeJson(user.getRole().name()),
                escapeJson(user.getName())
        );

        response.getWriter().write(jsonResponse);
    }

    private String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\b", "\\b")
                    .replace("\f", "\\f")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }
}
