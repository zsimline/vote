package org.vote.api.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.common.Email;

/**
 * 处理注册账户
 */
@WebServlet("/api/user/pwdforget")
public class Pwdforget extends BaseApi {
  private static final long serialVersionUID = 1L;

  private final String emailVerifyContent = "<div style=\"text-align: center;\">"
      + "<h2 style=\"color:#00bcd4\">用户电子邮件验证</h2>"
      + "<p><a style=\"font-size: 18px\" href=\"http://vote.zizaixian.top/user/profile?email=%s&code=%s\">点击链接重置密码</a><p>"
      + "<img src=\"http://vote.zizaixian.top/images/emailauth.jpg\">" + "<h4 style=\"color:#1d262d\">鹿鸣投票</h5>"
      + "<div>";

  public Pwdforget() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User user = getUserByEmail(request.getParameter("email"));
    if (user != null) {
      if (verifyEmail(response, user)) {
        complete(response, 2100);
      } else {
        complete(response, 2101);
      }
    } else {
      complete(response, 2102);
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

  /**
   * 根据邮件地址获取用户
   * 
   * @param emailAddress 邮件地址
   * @return 用户实例
   */
  private User getUserByEmail(String emailAddress) {    
    List<?> results = DBUtil.conditionQuery(User.class, "email", emailAddress);
    return results.isEmpty() ? null : (User) results.get(0);
  }
}