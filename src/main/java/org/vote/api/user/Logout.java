package org.vote.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseApi;
import org.vote.common.CookieFactory;
import org.vote.common.DBUtil;
import org.vote.common.Identify;

/**
 * 处理账户注销
 */
@WebServlet("/api/user/logout")
public class Logout extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Logout() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    long uid = Identify.userIdentify(request);
    User user = null;
    if (uid == -1L) {   // 用户未登录
      complete(response, 1802); return ;
    } else {  // 置空登录令牌
      user = (User) DBUtil.getInstanceById(User.class, uid);
      //user.setToken(null);
    }

    if (DBUtil.updateInstance(user)) {
      CookieFactory cookieFactory = new CookieFactory(response);
      cookieFactory.setCookie("uid", null, 0, "/");
      cookieFactory.setCookie("token", null, 0, "/");
      complete(response, 1800);
    } else {
      complete(response, 1801);
    }
  }
}