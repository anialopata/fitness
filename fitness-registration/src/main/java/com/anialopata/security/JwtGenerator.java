package com.anialopata.security;

import com.anialopata.domain.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Ania on 2018-08-05.
 */
@Component
@ConfigurationProperties
public class JwtGenerator {

    @Value("${fitness.security.secret}")
    private String secret;

    public String generate(Customer jwtUser) {
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUsername());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();

    }
}
