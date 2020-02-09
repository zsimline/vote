package org.vote.beans;

import java.io.Serializable;

/**
 * 微信用户信息表模型
 */
public class Wechat implements Serializable {
  private static final long serialVersionUID = 1L;

  // 微信用户OpenID
  private String id;

  // 微信用户昵称
  private String nickname;

  // 微信用户性别
  private boolean sex;

  // 微信用户所属国家
  private String country;

  // 微信用户所属省份
  private String province;

  // 微信用户所属城市
  private String city;

  // 微信用户头像地址
  private String headimgurl;

  // 微信用户投票令牌
  private String token;

  /**
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id 要设置的 id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return nickname
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * @param nickname 要设置的 nickname
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * @return sex
   */
  public boolean isSex() {
    return sex;
  }

  /**
   * @param sex 要设置的 sex
   */
  public void setSex(boolean sex) {
    this.sex = sex;
  }

  /**
   * @return country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country 要设置的 country
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return province
   */
  public String getProvince() {
    return province;
  }

  /**
   * @param province 要设置的 province
   */
  public void setProvince(String province) {
    this.province = province;
  }

  /**
   * @return city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city 要设置的 city
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return headimgurl
   */
  public String getHeadimgurl() {
    return headimgurl;
  }

  /**
   * @param headimgurl 要设置的 headimgurl
   */
  public void setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl;
  }

  /**
   * @return token
   */
  public String getToken() {
    return token;
  }

  /**
   * @param token 要设置的 token
   */
  public void setToken(String token) {
    this.token = token;
  }
}