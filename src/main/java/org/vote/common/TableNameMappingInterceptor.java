package org.vote.common;

import org.hibernate.EmptyInterceptor;

/**
 * 投票表名映射
 * 拦截Sql替换表名
 */
public class TableNameMappingInterceptor extends EmptyInterceptor {
  private static final long serialVersionUID = 1L;

  // 源表名
  private static final String srcTableName = "ticket";

  // 目标表名
  private String destTableName;

  public TableNameMappingInterceptor(String destTableName) {
    this.destTableName = destTableName;
  }

  @Override
  public String onPrepareStatement(String sql) {
    return sql.replaceAll(srcTableName, destTableName);
  }
}