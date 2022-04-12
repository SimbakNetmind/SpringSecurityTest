package com.r2system.app.SpringSecurityTest.utils;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import static com.r2system.app.SpringSecurityTest.constant.SecurityConstant.AUTH_HEADER;
import static com.r2system.app.SpringSecurityTest.constant.SecurityConstant.AUTH_TOKEN_PREFIX;

public class SecurityUtils {

    @Value("${app.jwt.secret}")
    private static String JWT_SECRET;

    public static String extractAuthTokenFromRequest(HttpServletRequest request){

        String beareToken = request.getHeader(AUTH_HEADER);
        if (StringUtils.isNoneEmpty(beareToken) && beareToken.startsWith(AUTH_TOKEN_PREFIX)){
            return beareToken.substring(7);
        }
        return null;
    }

//    public static Claims extractClaims(HttpServletRequest request){
//
//        String token = extractAuthTokenFromRequest(request);
//        if(token == null){
//
//            return null;
//        }
//        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
//
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
}
