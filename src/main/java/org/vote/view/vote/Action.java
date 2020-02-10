package org.vote.view.vote;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.beans.Wechat;
import org.vote.common.BaseView;
import org.vote.common.CookieFactory;
import org.vote.common.OAuth;

/**
 * 显示投票页面
 */
@WebServlet("/vote/action")
public class Action extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    String aid = request.getParameter("aid");
    Activity activity = (Activity)getInstanceById(Activity.class, aid);

    if (activity == null) {
      response.sendRedirect("/index/error");
    } else if (!hasAuth(request, response)) {
      response.sendRedirect(OAuth.getAuthCodeApiAddress(aid));
    } else {
      request.setAttribute("aid", aid);
      request.setAttribute("activity", activity);
      render(request, response, "/template/vote/action.jsp");
    }
  }

  private boolean hasAuth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    CookieFactory cookieFactory = new CookieFactory(request, response);
    HashMap<String, String> cookieMap = cookieFactory.cookiesToHashMap();
    
    // 获取微信用户 openid 与访问令牌
    String openid = cookieMap.get("openid");
    String token = cookieMap.get("token");

    // 未携带认证信息
    if (openid == null || token == null) {
      return false;
    }

    // 已携带认证信息但认证失败
    Wechat wechat = (Wechat) getInstanceById(Wechat.class, openid);
    if (wechat == null || !wechat.getToken().equals(token)) {
      return false;
    }

    return true;
  }
}
