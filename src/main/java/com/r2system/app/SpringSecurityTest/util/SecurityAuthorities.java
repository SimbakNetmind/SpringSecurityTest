package com.r2system.app.SpringSecurityTest.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.r2system.app.SpringSecurityTest.constant.SecurityConstant.*;

public class SecurityAuthorities {



    public static SimpleGrantedAuthority convertToAuthority(String role)
    {
        String formattedRole = role.startsWith(ROLE_PREFIX) ? role :ROLE_PREFIX + role; //org.apache.commons.lang3.StringUtils.EMPTY;/

        return new SimpleGrantedAuthority(formattedRole);
    }

    public static String extractAuthTokenFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX))
        {
            return bearerToken.substring(7);
        }
        return null;
    }
}
