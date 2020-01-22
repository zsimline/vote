package org.vote.common;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.util.StringHelper;

public class TableNameMapping extends DefaultNamingStrategy {
  private static final long serialVersionUID = 1L;

  public static final TableNameMapping INSTANCE = new TableNameMapping();

  @Override
  public String classToTableName(String className) {
    return StringHelper.unqualify(className).toLowerCase();
  } 
}
