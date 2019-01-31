package com.pwr.mobileapplications.expensemanager.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * Authentication using JWT
 */
public class AuthenticationService {

    private AuthenticationService() {
    }

    /**
     * Defines time in ms after JWT token is expired
     * 24 hours * 60 minutes * 60 seconds * 100 milliseconds
     *
     */
    private static final long EXPIRATION_TIME = 8640000;

    /**
     * Secret key for signing token
     */
    private static final String SIGNING_KEY = "SecretKey";

    /**
     * Token prefix used in request header
     */
    private static final String PREFIX = "Bearer";

    private static final String AUTHORIZATION = "Authorization";

    /**
     * Adds Authorization JWT to response header
     * @param res response that will have AUTHORIZATION header added
     * @param username username of user who will be authenticated
     */
    public static void addToken(HttpServletResponse res, String username) {
        // build token
        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
        // add it to header

        res.addHeader(AUTHORIZATION, PREFIX + " " + jwtToken);
        res.addHeader("Access-Control-Expose-Headers", AUTHORIZATION);
    }

    /**
     * Gets authentication using request signed with Authorization header containing JWT
     * @param req request
     * @return Authentication object authenticated with credentials from JWT
     */
    public static Authentication getAuthentication(HttpServletRequest req) {
        String token = req.getHeader(AUTHORIZATION);
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            }
        }
        return null;
    }
}
