package org.vote.common;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.MessagingException;

/**
 * 通用邮件工具
 */
public class Email {
  // SMTP邮件服务器地址
  private static final String smtpHost = "smtp.163.com";
  
  // 邮件发送者
  private static final String from = "zsimline@163.com";

  // 邮件会话对象
  private static Session session;

  // 初始化邮件客户端
  public static void initialize() {
    try {
    // 获取系统属性
    Properties properties = new Properties();

    // 设置发送邮件的邮件服务器
    properties.setProperty("mail.smtp.host", smtpHost);

    // 获取默认session对象
    session = Session.getDefaultInstance(properties);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 发送一封电子邮件
   * 
   * @param to 收信人地址
   * @return true/flase
   */
  public static boolean sendMail(String to) {
    // 获取系统属性
    Properties properties = new Properties();

    // 设置发送邮件的邮件服务器
    properties.setProperty("mail.smtp.host", smtpHost);

    // 获取默认session对象
    session = Session.getDefaultInstance(properties);
    try {
      // 创建默认的 MimeMessage 对象
      MimeMessage message = new MimeMessage(session);

      // 设置发信人
      message.setFrom(new InternetAddress(from));

      // 增加收信人
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // 设置邮件主题
      message.setSubject("鹿鸣投票-用户电子邮件验证");

      // 设置消息体
      message.setText("欢迎使用鹿鸣投票系统！！！");

      // 发送消息
      Transport.send(message);
      return true;
    } catch (MessagingException mex) {
      mex.printStackTrace();
      return false;
    }
  }
}