package org.vote.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseApi;
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
    if (Identify.userIdentify(request) != -1L) {
      request.getSession().removeAttribute("uid");
      response.setHeader("Set-Cookie", "JSESSIONID=FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF; Max-Age=0; Expires=Thu, 1-Jan-1970 00:00:00 GMT; Path=/; HttpOnly");
      complete(response, 1800);
    } else {  // 用户未登录
      complete(response, 1802);
    }
  }
}