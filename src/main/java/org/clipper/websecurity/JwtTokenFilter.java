package org.clipper.websecurity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            String token = jwtUtil.resolveToken(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtUtil.validateClaims(token);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(claims.getSubject(),"",new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            HashMap<String, Object> errorObj = new HashMap<>();
            errorObj.put("message", "authentication error");
            errorObj.put("details", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), errorObj);
        }

        filterChain.doFilter(request, response);
    }
}
