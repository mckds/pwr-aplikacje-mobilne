package com.pwr.mobileapplications.expensemanager.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

/**
 * Authentication using JWT
 */
public class AuthenticationService {
    /**
     * Defines time in ms after JWT token is expired
     * 24 hours * 60 minutes * 60 seconds * 100 milliseconds
     *
     */
    static final long EXPIRATION_TIME = 24 * 60 * 60 * 100;

    /**
     * Secret key for signing token
     */
    static final String SIGNING_KEY = "SecretKey";

    /**
     * Token prefix used in request header
     */
    static final String PREFIX = "Bearer";

    /**
     * Adds Authorization JWT to response header
     * @param res response that will have authorization header added
     * @param username username of user who will be authenticated
     */
    public static void addToken(HttpServletResponse res, String username) {
        // build token
        String JwtToken = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
        // add it to header
        res.addHeader("Authorization", PREFIX + " " + JwtToken);
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    /**
     * Gets authentication using request signed with Authorization header containing JWT
     * @param req request
     * @return Authentication object authenticated with credentials from JWT
     */
    public static Authentication getAuthentication(HttpServletRequest req) {
        Optional.ofNullable(req.getHeader("Authorization"))
                .ifPresent(token -> {
                    Optional.ofNullable(Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject())
                    .ifPresent(subject -> new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList()));
                });
        return null;
    }



}
