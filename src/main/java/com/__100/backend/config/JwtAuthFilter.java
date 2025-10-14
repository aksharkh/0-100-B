package com.__100.backend.config;

import com.__100.backend.service.service.CustomUserDetailsService;
import com.__100.backend.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private  final JwtUtil jwtUtil;
    private final CustomUserDetailsService UserDetailsService;


    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException{

        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwtToken = authHeader.substring(7);
            username = jwtUtil.extractUsername(jwtToken);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = UserDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
