package org.vote.api.vote;

import org.vote.common.UUIDTool;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseApi;

@WebServlet("/api/vote/oauth")
public class OAuth  extends BaseApi {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String code = request.getParameter("code");

    String state = request.getParameter("state");

    response.sendRedirect("https://baidu.com");
  }

  public static void getAuthCode(String aid) {
    String state = UUIDTool.getUUID();

    String redirectUri = "http://vote.zizaixian.top/vote/action?aid=" + aid;

   // String.format(authAddress, appid,  ,state);
  }
}
