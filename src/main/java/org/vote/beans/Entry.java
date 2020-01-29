package org.vote.beans;

import java.io.Serializable;

/**
 * 条目信息表模型
 */
public class Entry implements Serializable {
  private static final long serialVersionUID = 1L;

  // 条目ID
  private long id;

  // 活动ID
  private String aid;

  // 条目标题
  private String title;

  // 条目描述
  private String description;

  // 取得投票数
  private long acquisition;

  // 图片地址
  private String imgAddr;

  public Entry() {
    this.acquisition = 0;
  }

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
   * @return aid
   */
  public String getAid() {
    return aid;
  }

  /**
   * @param aid 要设置的 aid
   */
  public void setAid(String aid) {
    this.aid = aid;
  }

  /**
   * @return title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title 要设置的 title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description 要设置的 description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return acquisition
   */
  public long getAcquisition() {
    return acquisition;
  }

  /**
   * @param acquisition 要设置的 acquisition
   */
  public void setAcquisition(long acquisition) {
    this.acquisition = acquisition;
  }

  /**
   * @return imgAddr
   */
  public String getImgAddr() {
    return imgAddr;
  }

  /**
   * @param imgAddr 要设置的 imgAddr
   */
  public void setImgAddr(String imgAddr) {
    this.imgAddr = imgAddr;
  }
}