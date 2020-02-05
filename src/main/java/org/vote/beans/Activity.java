package org.vote.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动信息表模型
 */
public class Activity implements Serializable {
  private static final long serialVersionUID = 1L;

  // 活动ID
  private String id;

  // 活动标题
  private String title;

  // 发布者ID
  private long publisher;

  // 条目称谓
  private String suffix;

  // 条目量词
  private String quantifier;

  // 活动简介
  private String summary;

  // 投票开始时间
  private Date voteTimeStart;

  // 投票截止时间
  private Date voteTimeEnd;

  // 报名开始时间
  private Date applyTimeStart;

  // 报名截止时间
  private Date applyTimeEnd;

  // 最多选择的条目数
  private int maximum;

  // 条目总数
  private int sumEntry;

  // 投票总数
  private long sumVoted;

  // 总访问量
  private long sumVisited;

  // 宣传图片地址
  private String imgMain;

  // 其它必填项
  private String options;

  // 是否销毁
  private boolean destroyed;

  public Activity() {
    this.sumEntry = 0;
    this.sumVoted = 0;
    this.sumVisited = 0;
    this.destroyed = false;
  }

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
   * @return publisher
   */
  public long getPublisher() {
    return publisher;
  }

  /**
   * @param title 要设置的 title
   */
  public void setPublisher(long publisher) {
    this.publisher = publisher;
  }

  /**
   * @return suffix
   */
  public String getSuffix() {
    return suffix;
  }

  /**
   * @param suffix 要设置的 suffix
   */
  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  /**
   * @return quantifier
   */
  public String getQuantifier() {
    return quantifier;
  }

  /**
   * @param quantifier 要设置的 quantifier
   */
  public void setQuantifier(String quantifier) {
    this.quantifier = quantifier;
  }

  /**
   * @return summary
   */
  public String getSummary() {
    return summary;
  }

  /**
   * @param summary 要设置的 summary
   */
  public void setSummary(String summary) {
    this.summary = summary;
  }

  /**
   * @return voteTimeStart
   */
  public Date getVoteTimeStart() {
    return voteTimeStart;
  }

  /**
   * @param voteTimeStart 要设置的 voteTimeStart
   */
  public void setVoteTimeStart(Date voteTimeStart) {
    this.voteTimeStart = voteTimeStart;
  }

  /**
   * @return voteTimeEnd
   */
  public Date getVoteTimeEnd() {
    return voteTimeEnd;
  }

  /**
   * @param voteTimeEnd 要设置的 voteTimeEnd
   */
  public void setVoteTimeEnd(Date voteTimeEnd) {
    this.voteTimeEnd = voteTimeEnd;
  }

  /**
   * @return applyTimeEnd
   */
  public Date getApplyTimeEnd() {
    return applyTimeEnd;
  }

  /**
   * @param applyTimeEnd 要设置的 applyTimeEnd
   */
  public void setApplyTimeEnd(Date applyTimeEnd) {
    this.applyTimeEnd = applyTimeEnd;
  }

  /**
   * @return applyTimeStart
   */
  public Date getApplyTimeStart() {
    return applyTimeStart;
  }

  /**
   * @param applyTimeStart 要设置的 applyTimeStart
   */
  public void setApplyTimeStart(Date applyTimeStart) {
    this.applyTimeStart = applyTimeStart;
  }


  /**
   * @return maximum
   */
  public int getMaximum() {
    return maximum;
  }

  /**
   * @param maximum 要设置的 maximum
   */
  public void setMaximum(int maximum) {
    this.maximum = maximum;
  }

  /**
   * @return sumEntry
   */
  public int getSumEntry() {
    return sumEntry;
  }

  /**
   * @param sumEntry 要设置的 sumEntry
   */
  public void setSumEntry(int sumEntry) {
    this.sumEntry = sumEntry;
  }

  /**
   * @return sumVoted
   */
  public long getSumVoted() {
    return sumVoted;
  }

  /**
   * @param sumVoted 要设置的 sumVoted
   */
  public void setSumVoted(long sumVoted) {
    this.sumVoted = sumVoted;
  }

  /**
   * @return sumVisited
   */
  public long getSumVisited() {
    return sumVisited;
  }

  /**
   * @param sumVisited 要设置的 sumVisited
   */
  public void setSumVisited(long sumVisited) {
    this.sumVisited = sumVisited;
  }

  /**
   * @return imgMain
   */
  public String getImgMain() {
    return imgMain;
  }

  /**
   * @param imgMain 要设置的 imgMain
   */
  public void setImgMain(String imgMain) {
    this.imgMain = imgMain;
  }

  /**
   * @return options
   */
  public String getOptions() {
    return options;
  }

  /**
   * @param options 要设置的 options
   */
  public void setOptions(String options) {
    this.options = options;
  }

  /**
   * @return destroyed
   */
  public boolean getDestroyed() {
    return destroyed;
  }

  /**
   * @param destroyed 要设置的 destroyed
   */
  public void setDestroyed(boolean destroyed) {
    this.destroyed = destroyed;
  }
}
