package com.portfolio.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request, 
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    // obtener el string
    String token = getToken(request);

    // validamos el token
    if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
      System.out.println("ESTOY DENTRO");

      // obtenemos el token
      String username = jwtTokenProvider.getDataOfJwt(token);
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      // establecer la seguridad
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    } else {
      filterChain.doFilter(request, response);
    }
  }

  // obtenemos el token
  private String getToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    
    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }
}

