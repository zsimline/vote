package org.vote.api.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseApi;
import org.vote.common.MD5;
import org.vote.common.UUIDTool;
import org.vote.common.CookieFactory;
import org.vote.common.Utils;

/**
 * 处理账户登录
 */
@WebServlet("/api/user/login")
public class Login extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Login() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User userVerify = (User) Utils.postDataToObj(request, User.class);

    if (userVerify != null) {
      User user = getUserByEmail(userVerify.getEmail());

      // 验证账户是够存在
      if (user == null) {
        complete(response, 1202); return;
      }

      // 验证账户是否是已激活的
      if (!user.getIsActive()) {
        complete(response, 1204); return;
      }

      try {
        if (MD5.verify(userVerify.getPassword(), user.getPassword())) {
          // 生成登录令牌
          String token = UUIDTool.getUUID();
          user.setToken(token);

          if (updateInstance(user)) {
            CookieFactory cookieFactory = new CookieFactory(request, response);
            cookieFactory.setCookie("uid", String.valueOf(user.getId()));
            cookieFactory.setCookie("token", token);
            complete(response, 1200);
          } else {
            complete(response, 1205);
          }
        } else {
          complete(response, 1203);
        }
      } catch (Exception e) {
        e.printStackTrace();
        complete(response, 1201);
      }
    }
  }

  /**
   * 根据邮件地址获取用户
   * 
   * @param emailAddress 邮件地址
   * @return 用户实例
   */
  private User getUserByEmail(String emailAddress) {
    List<?> results = conditionQuery(User.class, "email", emailAddress);
    return results.isEmpty() ? null : (User) results.get(0);
  }
}