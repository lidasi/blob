package com.example.hitest.email;

import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;

import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class QEmail {
    /*
* 通过qq邮箱发送邮件,qq邮箱需要在设置里开启POP3/SMTP的授权，通过用户名+授权码方式才能发邮件
*/
    public static void qqSender(String toEmail, String username, int validate, String token) {
        MailSSLSocketFactory msf = null;
        try {
            msf = new MailSSLSocketFactory();
            msf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
        // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Properties props = new Properties();
        // 开启调试
        props.setProperty("mail.debug", "true");
        // 是否需要验证
        props.setProperty("mail.smtp.auth", "true");
        // 发送邮件服务器
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", msf);


        // 使用匿名内部类，用邮箱进行验证
        Session session = Session.getInstance(props, new Authenticator() {


            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
// 通过用户名和密码进行验证
                return new PasswordAuthentication("844133353@qq.com",
                        "sqzzytkzhhvzbcbb");
            }


        });
        Message message = new MimeMessage(session);
        try {
            // 设置邮件发送方
            message.setFrom(new InternetAddress("844133353@qq.com"));
            if(username != "") {
                // 设置邮件标题
                message.setSubject("测试");
                // 设置邮件内容
                message.setContent("测试", "text/html;charset=utf-8");

            } else if(validate > 0) {
                // 设置邮件标题
                message.setSubject("验证信息");
                // 设置邮件内容
                message.setContent("你好你在本站获取的邮箱验证码为:" + validate, "text/html;charset=utf-8");
            }
            // 设置邮件接收方
            message.addRecipient(RecipientType.TO, new InternetAddress(
                    toEmail));


            // 发送邮件
            Transport.send(message);


        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
