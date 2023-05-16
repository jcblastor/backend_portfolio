package com.portfolio.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.portfolio.exceptions.PortfolioAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
  // @Value("${app.jwt-secret}")
  Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  @Value("${app.jwt-expiration-milliseconds}")
  private int jwtExpirationInMs;
  
  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date newDate = new Date();
    Date expirationDate = new Date(newDate.getTime() + jwtExpirationInMs);

    String token = Jwts.builder()
                  .setSubject(username)
                  .setIssuedAt(new Date())
                  .setExpiration(expirationDate)
                  .signWith(jwtSecret)
                  .compact();

    return token;
  }

  public String getDataOfJwt(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody();
    //Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
  }

  public boolean validateToken(String token) {
    try {

      Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
			return true;

    } catch (JwtException ex ) {
      System.out.println(token);
      throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "Token invalido");
    } 
   /*  
    catch (MalformedJwtException ex ) {
      System.out.println(token);
      throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "Token invalido");
    } catch (ExpiredJwtException ex ) {
      System.out.println(token);
      throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "Token ha expirado");
    } catch (IllegalArgumentException ex ) {
      System.out.println(token);
      throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "Claims token esta vació");
    } catch (UnsupportedJwtException ex ) {
      System.out.println(token);
      throw new PortfolioAppException(HttpStatus.BAD_REQUEST, "Token no");
    }
     */
  }
}
