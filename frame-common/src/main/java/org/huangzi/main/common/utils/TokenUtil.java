package org.huangzi.main.common.utils;

import io.jsonwebtoken.*;
import org.huangzi.main.common.dto.ExceptionDto;
import org.huangzi.main.common.dto.TokenDto;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: XGLLHZ
 * @date: 2019/9/5 10:31
 * @description: token工具类
 */
public class TokenUtil {

    public static final String ISS = "XGLLHZ";

    //token有效时间 两小时
    public static final long EXPIRE_TIME = 2 * 60 * 60 * 1000;

    //签名密钥
    private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618";

    //JWT有效
    private static final Integer JWT_BE_BOT_OVERDUE_CODE = 1001;

    //JWT过期-无效
    private static final Integer JWT_BE_OVERDUE_CODE = 1002;

    /**
     * 创建 token
     * @param tokenDto 用户信息
     * @param audience 接收者
     * @return
     */
    public static String createToken(TokenDto tokenDto, String audience) {

        SecretKeySpec key = new SecretKeySpec(TOKEN_SECRET.getBytes(), "HmacSHA256");   //签名密钥

        long createTime = System.currentTimeMillis();   //JWT生成时间
        Date createDate = new Date(createTime);
        long expireTime = createTime + EXPIRE_TIME;   //JWT有效时间
        Date expireDate = new Date(expireTime);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", tokenDto.getUserId());
        map.put("userName", tokenDto.getUserName());
        map.put("userType", tokenDto.getUserType());

        JwtBuilder builder = Jwts.builder();   //new 一个JwtBuilder来设置JWT的body

        builder.setId(getUUID())   //设置jti(JWT ID)：是JWT的唯一标识，主要用来作为一次性token，从而回避重放攻击
                .setClaims(map)   //自定义属性
                .setIssuer(ISS)
                .signWith(key)   //指定密钥
                .setIssuedAt(createDate)   //JWT签发时间
                .setNotBefore(createDate)
                .setSubject(tokenDto.getUserId() + "")   //JWT的主体，即所有人，一般为userId等
                .setAudience(audience)   //接收者
                .setExpiration(expireDate);   //失效时间
        return builder.compact();   //开始压缩
    }

    /**
     * 验证 token
     * @param token
     * @return
     */
    public static Integer checkToken(String token) {

        SecretKeySpec key = new SecretKeySpec(TOKEN_SECRET.getBytes(), "HmacSHA256");   //签名密钥

        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            return JWT_BE_OVERDUE_CODE;
        }
        return JWT_BE_BOT_OVERDUE_CODE;
    }

    /**
     * 根据 key 获取用户信息
     * @param token
     * @param key
     * @return
     */
    public static Object getValue(String token, String key) {

        SecretKeySpec keys = new SecretKeySpec(TOKEN_SECRET.getBytes(), "HmacSHA256");   //签名密钥

        try {
            Claims claims = Jwts.parser().setSigningKey(keys).parseClaimsJws(token).getBody();
            return claims.get(key);
        } catch (JwtException e) {
            throw ExceptionDto.TOKEN_EXPIRE;
        }
    }

    /**
     * 生成唯一标识
     * @return
     */
    private final static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setUserId(1);
        tokenDto.setUserName("香格里拉皇子");
        tokenDto.setUserType(1);
        System.out.println(TokenUtil.createToken(tokenDto, "wx"));
        String s = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6MSwidXNlck5hbWUiOiLpppnmoLzph4zmi4nnmoflrZAiLCJ1c2VySWQiOjEsImlzcyI6IlhHTExIWiIsImlhdCI6MTU5MjQwMzU3MCwibmJmIjoxNTkyNDAzNTcwLCJzdWIiOiIxIiwiYXVkIjoid3giLCJleHAiOjE1OTI0MDM2MzB9.GhrKwrgjPeGJmo_oU4Mjhz7AOeBYZxiQzdIVY3ENa4c";
        System.out.println(TokenUtil.checkToken(s));
        System.out.println(getValue(s, "userName"));
    }

}
