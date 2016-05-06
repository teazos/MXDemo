package org.teazos.demo.mail;

/**
 * Created by mijunfeng on 16/5/6.
 */
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
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
        props.setProperty("mail.host", "smtp.163.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 设置环境信息
        Session session = Session.getInstance(props);

        // 创建邮件对象
        Message msg = new MimeMessage(session);
        try {
            msg.setSubject("JavaMail测试");

            // 设置邮件内容
            msg.setText("这是一封由<b>JavaMail</b>发送的邮件！");
            // 设置发件人
            msg.setFrom(new InternetAddress("teazos@163.com"));

            Transport transport = session.getTransport();
            // 连接邮件服务器
            transport.connect("teazos@163.com", "mijunfeng0");
            // 发送邮件
            transport.sendMessage(msg, new Address[]{new InternetAddress("teazos@163.com")});
            // 关闭连接
            transport.close();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
