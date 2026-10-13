package com.easypeasy.EPS_BACKEND.AUTH.filter;

import com.easypeasy.EPS_BACKEND.AUTH.service.JwtService;
import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import com.easypeasy.EPS_BACKEND.USERS.reposistory.UserReposistory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {




    private final JwtService jwtService;
    private final UserReposistory userReposistory;



    @Override


    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {




        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7).trim();

        String email = jwtService.extractEmail(token);

        User user = userReposistory.findByEmail(email)
                .orElse(null);

        if (user != null) {

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null, List.of(
                            new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
                    )
                    );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

        }


        filterChain.doFilter(request, response);
    }

    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserReposistory userReposistory) {

        this.jwtService = jwtService;
        this.userReposistory = userReposistory;
    }








}
