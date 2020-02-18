package org.vote.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.MD5;
import org.vote.common.UUIDTool;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.common.Utils;
import org.vote.common.Email;

/**
 * 处理注册账户
 */
@WebServlet("/api/user/register")
public class Register extends BaseApi {
  private static final long serialVersionUID = 1L;

  private final String emailVerifyContent = 
            "<div style=\"text-align: center;\">"
              + "<h2 style=\"color:#00bcd4\">用户电子邮件验证</h2>"
              + "<p><a style=\"font-size: 18px\" href=\"http://vote.zizaixian.top/user/activation?email=zsimline@163.com&code=f6248ca3dca775cce67edee9d9baaad5\">点击链接激活账户</a><p>"
              + "<img src=\"http://vote.zizaixian.top/images/emailauth.jpg\">"
              + "<h4 style=\"color:#1d262d\">鹿鸣投票</h5>"
          + "<div>";
  
  public Register() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User user = (User) Utils.postDataToObj(request, User.class);
    if (user != null) {
      user.setPassword(MD5.md5(user.getPassword()));
      user.setToken(UUIDTool.getUUID());
    } else {
      complete(response, 1101); return ;
    }

    // 存储用户注册信息
    if (DBUtil.saveInstance(user)) {
      if (verifyEmail(response, user)) {
        complete(response, 1100);
      } else {
        complete(response, 1102);
      }
    } else {
      complete(response, 1101);
    }
  }

  /**
   * 向用户邮箱发送验证信息
   * 
   * @param response Servlet 响应对象
   * @param user 用户实例
   */
  private boolean verifyEmail(HttpServletResponse response, User user) {
    String emailAddress = user.getEmail();
    String mailContent = String.format(emailVerifyContent, emailAddress, user.getPassword());
    return Email.sendMail(emailAddress, mailContent);
  }
}