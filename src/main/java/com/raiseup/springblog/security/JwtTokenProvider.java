package com.raiseup.springblog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
@Slf4j
public class JwtTokenProvider {

    private Key key;
    @PostConstruct
    public void init(){
        key=Keys.secretKeyFor(SignatureAlgorithm.HS512);
        log.info("KEY: {}",key);
    }

    public String generateToken(Authentication authentication ){
        User principal = (User) authentication.getPrincipal();
        String jwtString = Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();
        log.info("generateToken: {}",jwtString);
        return jwtString;
    }
    public boolean validateTocken(String jwt){
        log.info(jwt);
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
//        Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build().parseClaimsJwt(jwt);
//        Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
        return true;
    }

    public String getUsernameFromJwt(String jwt){
//        Claims claims= Jwts.parser().setSigningKey(key).parseClaimsJwt(jwt).getBody();
        Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
