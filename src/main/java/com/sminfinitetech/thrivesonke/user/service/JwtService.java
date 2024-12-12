package com.sminfinitetech.thrivesonke.user.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "17cb20cbdcb1fb93393e679e04d6d8869d4477628f7d00d1eadce10a2a8bf9bf2a26f78b836b68a4dfd5bf43322cd29bacd7de319b6e1526a4e022e1665065db85c0eb7a04ba9080e920f15642614a70885a01b84c5c51359e712c26dad90df26f6002200538adfead71012a3ec12c3c669c8cdbd832a978ad0bc67d072fe563f5f8caad0527f5a60c9a359747198a0149f77e15ebcd46d588f72aa81ef20ac7b7889cfebd8b85a06fff274c4d967372167e00b23ed3ca63eef67ef0ad72925041bb1bd492840ef982057eeee83234b25fb6582bbbbe187e92e4454626e1c942cd1dd9a089b0b6dcd0023cccba8e425cd0a4e183c07ade956bd635d9c2426f2b";

    // Generate token
    public String generateToken(String username, String password) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuer("thrivesonke")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .and()
                .signWith(generaKey())
                .compact();
    }

    private SecretKey generaKey() {
       byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(generaKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
      return extractExpiringDate(token).before(new Date());
    }

    private Date extractExpiringDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
