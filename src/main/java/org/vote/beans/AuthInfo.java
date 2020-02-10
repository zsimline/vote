package org.vote.beans;

import java.io.Serializable;

/**
 * 报名信息表模型
 */
public class AuthInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  // 网页授权接口调用凭证
  private String  access_token;

  // 用户唯一标识
  private String openid;

    /**
   * @return access_token
   */
  public String getAccessToken() {
    return access_token;
  }

  /**
   * @param access_token 要设置的 access_token
   */
  public void setAccessToken(String access_token) {
    this.access_token = access_token;
  }

  /**
   * @return openid
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * @param openid 要设置的 openid
   */
  public void setOpenid(String openid) {
    this.openid = openid;
  }
}