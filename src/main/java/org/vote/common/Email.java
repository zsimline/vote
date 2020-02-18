package org.vote.common;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;

/**
 * 通用邮件工具
 */
public class Email {
  // SMTP邮件服务器地址
  private static final String smtpHost = "smtp.163.com";

  // SMTP邮件服务器端口
  private static final String smtpPort = "465";
  
  // 邮件发送者地址
  private static final String from = "zsimline@163.com";

  // 邮件发送者密码
  private static final String password = "srlinewyyx01top";

  // 邮件会话对象
  private static Session session;

  /**
   * 初始化邮件客户端
   */
  public static void initialize() {
    try {
      // 设置邮件服务器属性
      Properties properties = new Properties();
      properties.setProperty("mail.smtp.host", smtpHost);
      properties.setProperty("mail.smtp.port", smtpPort);
      properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.setProperty("mail.smtp.socketFactory.port", smtpPort);

      // 设置邮件服务器需要验证
      properties.setProperty("mail.smtp.auth", "true");
      Authenticator authenticator = new Authenticator() {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
        }
      };

      // 获取默认session对象
      session = Session.getDefaultInstance(properties, authenticator);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 发送一封电子邮件
   * 
   * @param to 收信人地址
   * @param content 邮件内容
   * @return true/flase
   */
  public static boolean sendMail(String to, String content) {
    try {
      // 创建消息对象
      MimeMessage message = new MimeMessage(session);

      // 设置发信人
      message.setFrom(new InternetAddress(from));

      // 增加收信人
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // 设置邮件主题
      message.setSubject("鹿鸣投票-用户电子邮件验证");

      // 设置消息体
      message.setContent(content, "text/html;charset=UTF-8");

      // 发送消息
      Transport.send(message);
    } catch (MessagingException mex) {
      mex.printStackTrace();
      return false;
    }
    
    return true;
  }
}