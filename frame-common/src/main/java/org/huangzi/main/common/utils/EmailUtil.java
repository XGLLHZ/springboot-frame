package org.huangzi.main.common.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @author: XGLLHZ
 * @date: 2019/9/12 16:43
 * @description: 邮件工具类（163）
 */
public class EmailUtil {

    private static final String FROM = "香格里拉集团";   //发送方昵称;

    private static final String FROM_ACCOUNT = "guohaopp@163.com";   //发送方账号

    private static final String GRAN_CODE = "";   //授权码

    private static final String URL = "http://localhost:8081/api/admin/user/activate";   //激活链接地址

    /**
     * 邮件激活 假设用户账号是邮箱
     * @param userId
     * @param userName
     */
    public static void sendActivateLik(Integer userId, String userName) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            String content = "<p>您好！</p>"
                    + "<p>&nbsp;&nbsp;&nbsp;&nbsp;欢迎加入香格里拉集团，请点击此链接激活账号！</p>"
                    + "<p>&nbsp;&nbsp;&nbsp;&nbsp;<a target='_BLANK' href='" + URL + "?=" + userId + "'>"
                    + URL + "?id=" + userId + "</a></p>";
            message.setSubject("香格里拉集团 | 账号激活");   //邮件主题
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM_ACCOUNT, FROM, "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(userName));
            message.setContent(content, "text/html;charset=gb2312");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 邮件验证码 假设用户账号是邮箱
     * @param userName
     * @param code
     */
    public static void sendEmailCode(String userName, String code) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            String content = "<p>您好！</p>"
                    + "<p>&nbsp;&nbsp;&nbsp;&nbsp;欢迎加入香格里拉集团，您的验证码为：</p>"
                    + "<p>&nbsp;&nbsp;&nbsp;&nbsp;" + code + "</p>";
            message.setSubject("香格里拉集团 | 验证码");   //邮件主题
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM_ACCOUNT, FROM, "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(userName));
            message.setContent(content, "text/html;charset=gb2312");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 邮件配置
     * @return
     */
    public static Session getSession() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");   //指定发送邮件的邮箱协议
        properties.setProperty("mail.smtp.host", "smtp.163.com");   //指定smtp服务器
        properties.setProperty("mail.smtp.port", "25");   //端口号：25
        properties.setProperty("mail.smtp.auth", "true");   //指定是否需要smtp验证
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_ACCOUNT, GRAN_CODE);
            }
        });
        return session;
    }

    public static void main(String[] args) {
        /*SYSUser sysUser = new SYSUser();
        sysUser.setId(1);
        sysUser.setUsername("13622119236@163.com");
        String code =  RandomUtil.sixCode();
        *//*EmailUtil.sendActivateLik(sysUser);*//*
        EmailUtil.sendEmailCode(sysUser, code);*/
    }

}
