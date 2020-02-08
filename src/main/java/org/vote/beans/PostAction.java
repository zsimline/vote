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
}