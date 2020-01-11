package org.vote.beans;

import java.util.Date;

/**
 * 投票信息表模型
 */
public class Ticket {
  // 投票ID
  private long id;

  // 投票者OpenID
  private String openid;

  // 投向条目的ID
  private long whom;

  // 投票者昵称
  private String nickname;

  // 投票者性别
  private boolean sex;

  // 投票者所属国家
  private String country;

  // 投票者所属省份
  private String province;

  // 投票者所属城市
  private String city;

  // 投票者头像地址
  private String headimgurl;

  // 投票时间
  private Date timestamp;

  // IP地址
  private String ipaddr;

  /**
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id 要设置的 id
   */
  public void setId(long id) {
    this.id = id;
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

  /**
   * @return whom
   */
  public long getWhom() {
    return whom;
  }

  /**
   * @param whom 要设置的 whom
   */
  public void setWhom(long whom) {
    this.whom = whom;
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
  public boolean getSex() {
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
   * @return timestamp
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * @param timestamp 要设置的 timestamp
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * @return ipaddr
   */
  public String getIpaddr() {
    return ipaddr;
  }

  /**
   * @param ipaddr 要设置的 ipaddr
   */
  public void setIpaddr(String ipaddr) {
    this.ipaddr = ipaddr;
  }
}
