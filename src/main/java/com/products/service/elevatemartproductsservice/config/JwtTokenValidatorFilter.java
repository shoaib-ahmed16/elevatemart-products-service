package com.products.service.elevatemartproductsservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

@Slf4j
@Component
public class JwtTokenValidatorFilter extends OncePerRequestFilter {


    @Value("${jwt.security.token.secret_key}")
    public String JWT_SECRET_KEY;


    @Value("${jwt.security.token.issuer}")
    public String jwtIssuer;

    @Value("${jwt.security.token.subject}")
    public String jwtSubject;



    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        String header = req.getHeader("Authorization");
        String username = null;
        String authToken = null;
        String authorities;
        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.split(" ")[1];
            try {
                SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes());
                Claims claims=getClaims(key,authToken);
                username =String.valueOf(claims.get("username"));
                authorities=String.valueOf(claims.get("authorities"));
                String issuer = claims.getIssuer();
                String subject = claims.getSubject();
                Authentication auth= new UsernamePasswordAuthenticationToken(username,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch(Exception e){
                log.error("Authentication Failed. Username or Password not valid. Exception : {}",e.getMessage());
                throw new RuntimeException("Authentication Failed. Username or Password not valid. Exception: "+e.getMessage());
            }
        } else {
            logger.warn("Couldn't find bearer string, header will be ignored");
        }
        filterChain.doFilter(req, res);
    }

    private Claims getClaims(SecretKey key, String jwtToken){
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
    }

}
