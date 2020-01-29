package org.vote.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
    String jsonStr = Utils.inputToJsonStr(request);

    if (jsonStr != null) {
      Gson gson = new Gson();
      User user = gson.fromJson(jsonStr, User.class);

      // 取用户密码的哈希摘要
      try {
        user.setPassword(MD5.md5(user.getPassword()));
      } catch (Exception e) {
        e.printStackTrace();
        completed(response, 1103);
      }

      if (saveInstance(user)) {
        verifyEmail(response, user);
        completed(response, 1100);
      } else {
        completed(response, 1101);
      }
    } else {
      completed(response, 1102);
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
      completed(response, 1104);
    }
  }
}
