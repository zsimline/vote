package org.vote.common;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.vote.beans.AuthInfo;
import org.vote.beans.Wechat;

public class OAuth {
  // 微信公众号AppId
  private static final String appid = "wx009793a980bbfa74";

  // 微信公众号秘钥
  private static final String secret = "96e410410d32e25d25a687dea7ec0afd";

  // 本地服务器OAuth认证接口
  private static final String redirectUri = "http://vote.zizaixian.top/api/vote/auth";

  // 获取认证码接口地址模板
  private static final String authCodeApiAddressTpl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";
  
  // 获取访问令牌与 openid 接口地址模板
  private static final String authInfoApiAddressTpl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

  // 获取用户信息接口地址模板
  private static final String userInfoApiAddressTpl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

  // json 解析器
  private static final Gson gson = new Gson();

  /**
   * 获取认证地址
   *
   * @return 获取认证码的接口地址
   */
  public static String getAuthCodeApiAddress(String state) {
    return  String.format(authCodeApiAddressTpl, appid, redirectUri, state);
  }

  /**
   * 获取 openid 与 access_token
   * 
   * @param code 认证码
   * @return 包含openid 与 access_token的认证信息
   */
  public static  AuthInfo getAuthInfo(String code) {
    String  authInfoApiAddress  = String.format(authInfoApiAddressTpl, appid, secret, code);
    String jsonStr = Utils.httpsGet(authInfoApiAddress );
    try {
      return (AuthInfo) gson.fromJson(jsonStr, AuthInfo.class);
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
      return new AuthInfo();
    }
  }
  
  /**
   * 获取微信用户基本信息
   * 
   * @param authInfo 包含openid 与 access_token的认证信息
   * @return 用户基本信息
   */
  public static Wechat getUserInfo(AuthInfo authInfo) {
    String userInfoApiAddress =  String.format(userInfoApiAddressTpl, authInfo.getAccessToken(), authInfo.getOpenid());
    String jsonStr = Utils.httpsGet(userInfoApiAddress);
    try {
      return (Wechat) gson.fromJson(jsonStr, Wechat.class);
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
      return null;
    }
  }
}