package org.vote.api.wechat;

import org.vote.common.UUIDTool;

public class OAuth {
  private static final String appid = "wx009793a980bbfa74";

  private static final String redirectUri = "http://vote.zizaixian.top/vote/wechat/oauth";

  private static final String authAddress = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";

  public static void getAuthCode(String aid) {
    String.format(authAddress, appid, redirectUri, state);
  }
}
