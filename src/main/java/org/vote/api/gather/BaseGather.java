package org.vote.api.gather;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.vote.common.BaseApi;
import org.vote.common.HibernateUtil;

/**
 * 基础汇总类
 * 所有汇总类继承自此类
 */
public class BaseGather extends BaseApi {
  private static final long serialVersionUID = 1L;
  
  /**
   * 根据Sql语句执行汇总
  *
   * @param sql 查询语句
   * @param interceptor 表名映射器
   * @return 列表格式汇总结果
   */
  protected Object doGather(String sql, Interceptor interceptor) {
    Session session = null;
    List <?>  results = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession(interceptor);
      Query query = session.createSQLQuery(sql);
      results = query.list();
    } catch (HibernateException e) {
      e.printStackTrace();
      return Collections.emptyList();
    } finally {
      if (session != null) {
        session.close();
      }
    }

    return results;
  }
}