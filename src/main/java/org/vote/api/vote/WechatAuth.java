package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.AuthInfo;
import org.vote.beans.Wechat;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.common.OAuth;

@WebServlet("/api/vote/auth")
public class WechatAuth extends BaseApi {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    String code = request.getParameter("code");
    String state  =request.getParameter("state");

    if (code == null || state == null) {
      response.sendRedirect("/index/error");
    } else {      
      AuthInfo authInfo = OAuth.getAuthInfo(code);
      if (authInfo.getOpenid() == null || authInfo.getAccessToken() == null) {
        response.sendRedirect("/index/error");
      } else {
        Wechat wechat = OAuth.getUserInfo(authInfo);
        if (wechat == null) {
          response.sendRedirect("/index/error");
        } else {
          DBUtil.saveOrUpdateInstance(wechat);
          request.getSession().setAttribute("openid", wechat.getOpenid());
          response.sendRedirect("/vote/action?aid=" + state);
        }
      }
    }
  }
}