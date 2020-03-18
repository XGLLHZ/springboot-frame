package org.huangzi.main.common.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

/**
 * @author: XGLLHZ
 * @date: 2019/9/5 10:31
 * @description: token工具类
 */
public class TokenUtil {

    //token有效时间 两小时
    public static final long EXPIRE_TIME = 7200000;

    //签名密钥
    private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618";

    //JWT有效
    private static final Integer JWT_BE_BOT_OVERDUE_CODE = 1001;

    //JWT过期-无效
    private static final Integer JWT_BE_OVERDUE_CODE = 1002;

    /**
     * 生成JWS      JWT签名后称为JWS
     * @param subject 用户id 即生成的JWS的唯一所有人
     * @return
     */
    public static String createJWS(String subject) {

        SecretKeySpec key = new SecretKeySpec(TOKEN_SECRET.getBytes(), "HmacSHA256");   //签名密钥

        long createTime = System.currentTimeMillis();   //JWT生成时间
        Date createDate = new Date(createTime);
        long expireTime = createTime + EXPIRE_TIME;   //JWT有效时间
        Date expireDate = new Date(expireTime);

        JwtBuilder builder = Jwts.builder();   //new 一个JwtBuilder来设置JWT的body

        builder.setId(getUUID())   //设置jti(JWT ID)：是JWT的唯一标识，主要用来作为一次性token，从而回避重放攻击
                .signWith(key)   //指定密钥
                .setIssuedAt(createDate)   //JWT签发时间
                .setSubject(subject)   //JWT的主体，即所有人，一般为userId等
                .setExpiration(expireDate);   //失效时间
        return builder.compact();   //开始压缩
    }

    /**
     * 验证JWS
     *      验证JWT时的签名密钥跟生成时的签名密钥相同，且生成将其存入数据库，
     *      所以验证时如果抛出异常，只能是过期
     * @param jwsString
     * @return 1001：有效；1002：无效-过期
     */
    public static Integer checkJWS(String jwsString) {

        SecretKeySpec key = new SecretKeySpec(TOKEN_SECRET.getBytes(), "HmacSHA256");   //签名密钥

        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(jwsString);
        } catch (JwtException e) {
            return JWT_BE_OVERDUE_CODE;
        }
        return JWT_BE_BOT_OVERDUE_CODE;
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
        String subject = "1";
        System.out.println(TokenUtil.createJWS(subject));
        /*String s = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIlVzZXJBY2NvdW50Ijoi" +
                "YWRtaW4iLCJqdGkiOiI4NDU5YjljMThkMTU0NGZmOTZkNmQ1YTI2ZTNjZDJi" +
                "NiIsImlhdCI6MTU2NzY2NzY3OCwic3ViIjoiMSIsImV4cCI6MTU2NzY2Nzcz" +
                "OH0.-nqh-ppYQkhSvUXBg7rS-sWRSNxHqOWQ2WmEhkp6Pb0";
        System.out.println(TokenUtil.checkJWS(s));*/
    }

}
