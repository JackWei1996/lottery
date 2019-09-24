package com.jack.lottery.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {


    /**
     * 过期时间为10天，10天后需要重新登录
     */
    private static final int expireDay = 10;
    public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);;

    public static String generateJwt(Map<String, Object> claimMap) {
        Date now = new Date();
        return Jwts.builder().setClaims(claimMap)
                // 设置过期时间
                .setExpiration(DateUtils.addDays(now, expireDay))
                // 不处理签发之前的jwt
                .setNotBefore(now)
                // 设置签发时间
                .setIssuedAt(now)
                .signWith(key).compact();
    }

    public static Claims parse(String jwsStr) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwsStr).getBody();
    }
}