package org.vote.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Wechat;
import org.vote.common.UUIDTool;

@WebServlet("/api/vote/auth")
public class OAuth extends BaseApi {
  private static final long serialVersionUID = 1L;

  // 微信公众号AppId
  private static final String appid = "wx009793a980bbfa74";

  // 微信公众号秘钥
  private static final String secret = "96e410410d32e25d25a687dea7ec0afd";

  // 本地服务器OAuth认证接口
  private static final String redirectUri = "http://vote.zizaixian.top/api/vote/auth";

  // OAuth验证秘钥
  private static final String theState = "123";

  // 获取认证码接口地址模板
  private static final String authCodeApiAddressTpl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";

  //  获取认证码接口地址
  private static final String authCodeApiAddress = String.format(authCodeApiAddressTpl, appid, redirectUri, theState);
  
  // 获取访问令牌与 openid 接口地址模板
  private static final String openidApiAddressTpl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

  // 获取用户信息接口地址模板
  private static final String userInfoApiAddressTpl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

  public static String getAuthCodeApiAddress() {
    return authCodeApiAddress;
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    String code = request.getParameter("code");
    String state  =request.getParameter("state");

    if (code == null || state == null || !state.equals(theState)) {
      System.out.println("认证失败");
    } else {
      String  openidApiAddress = String.format(openidApiAddressTpl, appid, secret, code);
      System.out.println(Utils.httpsGet(openidApiAddress));
    }
  }
}
