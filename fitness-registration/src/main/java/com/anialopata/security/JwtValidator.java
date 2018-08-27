package com.anialopata.security;

import com.anialopata.domain.Customer;
import com.anialopata.exception.FitnessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by Ania on 2018-08-05.
 */
@ConfigurationProperties
@Component
public class JwtValidator {

    @Value("${fitness.security.secret}")
    private String secret;

    public Customer validate(String token) {

        Customer jwtUser;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new Customer();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String)body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch(Exception ex) {
            throw new FitnessException("Problem with validating customer");
        }

        return jwtUser;
    }
}
