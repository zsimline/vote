package org.vote.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.MD5;
import org.vote.common.BaseApi;
import org.vote.common.Utils;
import org.vote.common.Email;

/**
 * 处理注册账户
 */
@WebServlet("/api/user/register")
public class Register extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Register() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User user = (User) Utils.postDataToObj(request, User.class);

    if (user != null) {
      user.setPassword(MD5.md5(user.getPassword()));
    } 

    if (saveInstance(user)) {
      verifyEmail(response, user);
      complete(response, 1100);
    } else {
      complete(response, 1101);
    }
  }

  /**
   * 向用户邮箱发送验证信息
   * 
   * @param response Servlet 响应对象
   * @param user 用户实例
   * @throws IOException
   * @throws ServletException
   */
  private void verifyEmail(HttpServletResponse response, User user) throws ServletException, IOException {
    String emailAddress = user.getEmail();
    String mailContent = "<a href=\"http://vote.zizaixian.top/user/activation?"
                         + "email=" + emailAddress + "&code=" + user.getPassword()
                         + "\">点击链接激活账户</a>";

    if (!Email.sendMail(emailAddress, mailContent)) {
      complete(response, 1102);
    }
  }
}