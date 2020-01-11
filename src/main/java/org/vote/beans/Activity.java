package org.vote.beans;

/**
 * 活动信息表模型
 */
public class Activity {
  // 活动ID
  private String id;

  // 活动标题
  private String title;

  // 条目称谓
  private String suffix;

  // 活动描述
  private String description;

  // 开始时间
  private long timeStart;

  // 截止时间
  private long timeEnd;

  // 最多选择的条目数
  private int maxium;

  // 条目总数
  private int sumEntry;

  // 投票总数
  private long sumVoted;

  // 总访问量
  private long sumVisited;

  // 宣传图片地址
  private String imgAddr;

  // 其它必填项
  private String options;

  // 是否销毁
  private boolean destroyed;

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
   * @return timeStart
   */
  public long getTimeStart() {
    return timeStart;
  }

  /**
   * @param timeStart 要设置的 timeStart
   */
  public void setTimeStart(long timeStart) {
    this.timeStart = timeStart;
  }

  /**
   * @return timeEnd
   */
  public long getTimeEnd() {
    return timeEnd;
  }

  /**
   * @param timeEnd 要设置的 timeEnd
   */
  public void setTimeEnd(long timeEnd) {
    this.timeEnd = timeEnd;
  }

  /**
   * @return maxium
   */
  public int getMaxium() {
    return maxium;
  }

  /**
   * @param maxium 要设置的 maxium
   */
  public void setMaxium(int maxium) {
    this.maxium = maxium;
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
