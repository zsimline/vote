package org.vote.common;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * 基础Servlet类
 * 所有Servlet类继承自此类
 */
public class BaseServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * 根据ID获取实例
   * 
   * @param clazz 实例类
   * @param id 实例ID
   * @return 实例
   */
  public Object getInstanceById(Class<?> clazz, String id) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      return session.get(clazz, id);
    } catch (HibernateException e) {
      e.printStackTrace();
      return null;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * 根据ID获取实例
   * 
   * @param clazz 实例类
   * @param id 实例ID
   * @return
   */
  public Object getInstanceById(Class<?> clazz, long id) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      return session.get(clazz, id);
    } catch (HibernateException e) {
      e.printStackTrace();
      return null;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * 更新数据库实例
   * 
   * @param instance 数据实例
   * @return 更新数据实例成功/失败
   */
  protected boolean updateInstance(Object instance) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      transaction = session.beginTransaction();
      transaction.begin();
      session.update(instance);
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      return false;
    } finally {
      if (session != null) {
        session.close();
      }
    }

    return true;
  }


  /**
   * 按条件查询
   * 
   * @param clazz   实例类
   * @param keys    条件名集合
   * @param values 条件值集合
   * @return 查询结果集
   */
  protected List<?> conditionQuery(Class<?> clazz, String[] keys, Object[] values) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();

      // 创建条件容器并附加条件
      Criteria criteria = session.createCriteria(clazz);  
      for (int i = 0; i < keys.length; i++) {
        criteria.add(Restrictions.eq(keys[i], values[i]));
      }

      return criteria.list();
    } catch (HibernateException e) {
      e.printStackTrace();
      return Collections.emptyList();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * 按条件查询
   * 
   * @param clazz   实例类
   * @param conditonName 条件名
   * @param conditonValue  条件值
   * @return 查询结果集
   */
  protected List<?> conditionQuery(Class<?> clazz, String conditonName, Object conditonValue) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();

      // 创建条件容器并附加条件
      Criteria criteria = session.createCriteria(clazz);  
      criteria.add(Restrictions.eq(conditonName, conditonValue));

      return criteria.list();
    } catch (HibernateException e) {
      e.printStackTrace();
      return Collections.emptyList();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
}