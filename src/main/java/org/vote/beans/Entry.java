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

  // 参赛编号
  private int number;

  // 条目标题
  private String title;

  // 条目介绍
  private String introduction;

  // 取得投票数
  private long acquisition;

  // 图片地址
  private String imgEntry;

  // 条目是否冻结
  private boolean isFreeze;

  public Entry() {
    this.acquisition = 0;
    this.isFreeze = false;
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
   * @return number
   */
  public int getNumber() {
    return number;
  }

  /**
   * @param number 要设置的 number
   */
  public void setNumber(int number) {
    this.number = number;
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
   * @return introduction
   */
  public String getIntroduction() {
    return introduction;
  }

  /**
   * @param introduction 要设置的 introduction
   */
  public void setIntroduction(String introduction) {
    this.introduction = introduction;
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
   * @return imgEntry
   */
  public String getImgEntry() {
    return imgEntry;
  }

  /**
   * @param imgEntry 要设置的 imgEntry
   */
  public void setImgEntry(String imgEntry) {
    this.imgEntry = imgEntry;
  }

  /**
   * @return isFreeze
   */
  public boolean getIsFreeze() {
    return isFreeze;
  }

  /**
   * @param isFreeze 要设置的 isFreeze
   */
  public void setIsFreeze(boolean isFreeze) {
    this.isFreeze = isFreeze;
  }
}