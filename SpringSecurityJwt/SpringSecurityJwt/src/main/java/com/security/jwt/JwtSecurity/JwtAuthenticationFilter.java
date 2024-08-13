package com.security.jwt.JwtSecurity;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.jwt.service.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // Log the header value
        logger.info("Authorization Header: {}", requestHeader);

        // Check if the Authorization header is present and starts with "Bearer "
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            // Extract the token from the header
            token = requestHeader.substring(7);

            try {
                // Get the username from the token
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.error("Illegal argument while fetching the username from token", e);
            } catch (ExpiredJwtException e) {
                logger.error("JWT token has expired", e);
            } catch (MalformedJwtException e) {
                logger.error("JWT token is malformed", e);
            } catch (Exception e) {
                logger.error("An error occurred while processing the JWT token", e);
            }
        } else {
            logger.warn("Invalid Authorization header format. It should start with 'Bearer '");
        }

        // Proceed with token validation and authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Fetch user details from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            boolean isTokenValid = this.jwtHelper.validateToken(token, userDetails);

            if (isTokenValid) {
                // Set the authentication in the context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.warn("JWT token validation failed");
            }
        }

        // Proceed with the next filter in the chain
        filterChain.doFilter(request, response);
    }

}
	
	