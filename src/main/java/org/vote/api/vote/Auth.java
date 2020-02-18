package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.AuthInfo;
import org.vote.beans.Wechat;
import org.vote.common.BaseApi;
import org.vote.common.CookieFactory;
import org.vote.common.DBUtil;
import org.vote.common.UUIDTool;
import org.vote.common.OAuth;

@WebServlet("/api/vote/auth")
public class Auth extends BaseApi {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    String code = request.getParameter("code");
    String state  =request.getParameter("state");

    if (code == null || state == null) {
      System.out.println("认证失败");
    } else {      
      AuthInfo authInfo = OAuth.getAuthInfo(code);
      if (authInfo.getOpenid() == null || authInfo.getAccessToken() == null) {
        response.sendRedirect("/index/error");
      } else {
        Wechat wechat = OAuth.getUserInfo(authInfo);
        
        if (wechat == null) {
          response.sendRedirect("/index/error");
        } else {
          wechat.setToken(UUIDTool.getUUID());
          if (DBUtil.saveOrUpdateInstance(wechat)) {
            CookieFactory cookieFactory = new CookieFactory(response);
            cookieFactory.setCookie("openid", wechat.getOpenid());
            cookieFactory.setCookie("token", wechat.getToken());
            response.sendRedirect("/vote/action?aid=" + state);
          } else {
            response.sendRedirect("/index/error");
          }
        }
      }
    }
  }
}