package org.vote.beans;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {
  private static final long serialVersionUID = -4574956879928378401L;

  // 页面总数
  private int sumPages;

  // 结果集合
  private List<?> results;

  /**
   * @return sumPages
   */
  public int getSumPages() {
    return sumPages;
  }

  /**
   * @param sumPages 要设置的 sumPages
   */
  public void setSumPages(int sumPages) {
    this.sumPages = sumPages;
  }

  /**
   * @return results
   */
  public List<?> getResults() {
    return results;
  }

  /**
   * @param results 要设置的 results
   */
  public void setResults(List<?> results) {
    this.results = results;
  }
}