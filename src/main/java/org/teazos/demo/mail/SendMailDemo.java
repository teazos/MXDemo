package org.teazos.demo.mail;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by mijunfeng on 16/4/19.
 * 发送邮件例子
 */
public class SendMailDemo {

    public static void main(String[] args) throws MessagingException {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "[发件服务器]");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        try {
            msg.setSubject("JavaMail测试");

            // 设置邮件内容
            msg.setText("这是一封由JavaMail发送的邮件！");
            // 设置发件人
            msg.setFrom(new InternetAddress("[发件人邮箱]"));

            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect("[用户名]", "[密码]");
            // 发送邮件
            transport.sendMessage(msg, new Address[]{new InternetAddress("[收件人地址]")});
            // 关闭连接
            transport.close();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
