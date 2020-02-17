package org.vote.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseApi;
import org.vote.common.CookieFactory;

/**
 * 处理账户注销
 */
@WebServlet("/api/user/logout")
public class Logout extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Logout() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    // 置空登录令牌
    long uid = Identify.userIdentify(request, response);
    User user = (User) getInstanceById(User.class, uid);
    user.setToken(null);

    if (updateInstance(user)) {
      CookieFactory cookieFactory = new CookieFactory(request, response);
      cookieFactory.setCookie("uid", null, 0, "/");
      cookieFactory.setCookie("token", null, 0, "/");
      complete(response, 1800);
    } else {
      complete(response, 1801);
    }
  }
}