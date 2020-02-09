package org.vote.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * 投票信息表模型
 */
public class Ticket implements Serializable {
  private static final long serialVersionUID = 1L;

  // 投票ID
  private long id;

  // 投票者OpenID
  private String openid;

  // 投向条目的ID
  private long whom;

  // 投票时间
  private Date timestamp;

  // IP地址
  private String ip;

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
   * @return ip
   */
  public String getIp() {
    return ip;
  }

  /**
   * @param ip 要设置的 ip
   */
  public void setIp(String ip) {
    this.ip = ip;
  }
}
