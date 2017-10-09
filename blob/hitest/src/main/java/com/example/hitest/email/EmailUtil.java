package com.example.hitest.email;


import com.example.hitest.Util.PropUtil;

/**
 * 发送邮件辅助类
 */
public class EmailUtil {

    /**
     * 以文本格式发送邮件
     * 
     * @param toAddress
     *            邮件接收者的地址
     * @param subject
     *            待发送的邮件的主题
     * @param content
     *            待发送的邮件的文本内容
     * @return Boolean 发送结果
     */
    public static boolean sendTextMail(String toAddress, int content) {

        String mailserverhost = "192.168.0.250";
        String mailserverprot = "25";
        String validate = "true";
        String username = "lidasi";
        String password = "lds.2015";
        String fromaddress = "lidasi@sis.sh.cn";

        // 设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost(mailserverhost);
        mailInfo.setMailServerPort(mailserverprot);
        mailInfo.setValidate(Boolean.parseBoolean(validate));
        mailInfo.setUserName(username);
        mailInfo.setPassword(password);
        mailInfo.setFromAddress(fromaddress);
        mailInfo.setToAddress(toAddress);
        mailInfo.setSubject("badUncle");
        mailInfo.setContent("你在本站获取的验证码是："+content);
        // 发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        boolean mailRes = sms.sendTextMail(mailInfo);// 发送文本格式邮件
        if (mailRes) {
            // 发送成功
            return true;
        } else {
            // 发送失败
            return false;
        }
    }
}
