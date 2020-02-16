package org.vote.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 用户上传的投票数据
 */
public class PostAction implements Serializable {
  private static final long serialVersionUID = 1L;

  // 活动ID
  private String aid;

  // 条目ID数组
  private List<Integer> ids;

  // 投票理由
  private String reason;

  public PostAction() {
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
   * @return ids
   */
  public List<Integer> getIds() {
    return ids;
  }

  /**
   * @param ids 要设置的 ids
   */
  public void setIds(List<Integer> ids) {
    this.ids = ids;
  }

  /**
   * @return reason
   */
  public String getReason() {
    return reason;
  }

  /**
   * @param reason 要设置的 reason
   */
  public void setReason(String reason) {
    this.reason = reason;
  }
}