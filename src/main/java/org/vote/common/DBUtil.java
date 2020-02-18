package org.vote.common;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.vote.common.HibernateUtil;

/**
 * 基础数据库工具类 包含常用的操作数据库的方法
 */
public class DBUtil {
  /**
   * 根据ID获取实例
   * 
   * @param clazz 实例类
   * @param id 实例ID
   * @return 实例
   */
  public static Object getInstanceById(Class<?> clazz, String id) {
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
  public static Object getInstanceById(Class<?> clazz, long id) {
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
   * 保存数据实例
   * 
   * @param instance 数据实例
   * @return 操作执行成功/失败
   */
  public static boolean saveInstance(Object instance) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      transaction = session.beginTransaction();
      transaction.begin();
      session.save(instance);
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
   * 保存数据实例
   * 
   * @param instance 数据实例
   * @return 操作执行成功/失败
   */
  public static boolean deleteInstance(Object instance) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      transaction = session.beginTransaction();
      transaction.begin();
      session.delete(instance);
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
   * 更新数据库实例
   * 
   * @param instance 数据实例
   * @return 更新数据实例成功/失败
   */
  public static boolean updateInstance(Object instance) {
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
   * 保存或更新数据实例
   * 
   * @param instance 数据实例
   * @return 操作执行成功/失败
   */
  public static boolean saveOrUpdateInstance(Object instance) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      transaction = session.beginTransaction();
      transaction.begin();
      session.saveOrUpdate(instance);
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
  public static List<?> conditionQuery(Class<?> clazz, String[] keys, Object[] values) {
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
  public static List<?> conditionQuery(Class<?> clazz, String conditonName, Object conditonValue) {
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

  /**
   * 按条件分页查询
   * 
   * @param clazz  实例类
   * @param keys   条件名集合
   * @param values 条件值集合
   * @param page   当前页面索引
   * @param max    单页最大数量
   * @return 查询结果集
   */
  public static List<?> paginationQuery(Class<?> clazz, String[] keys, Object[] values, int page, int max) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();

      // 创建条件容器并添加条件
      Criteria criteria = session.createCriteria(clazz);  
      for (int i = 0; i < keys.length; i++) {
        criteria.add(Restrictions.eq(keys[i], values[i]));
      }
    
      // 设置起始页面与单页最大选择数量
      criteria.setFirstResult((page-1) * max);
      criteria.setMaxResults(max);

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
   * 统计行数
   * 
   * @param clazz 实例类
   * @return 行数
   */
  public static int countRows(Class<?> clazz) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();

      // 统计行数
      Criteria criteria = session.createCriteria(clazz);
      int totalRows = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
      
      return totalRows;
    } catch (HibernateException e) {
      e.printStackTrace();
      return 0;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
  
  /**
   * 按条件统计行数
   * 
   * @param clazz  实例类
   * @param keys   条件名集合
   * @param values 条件值集合
   * @return 行数
   */
  public static int countRows(Class<?> clazz, String[] keys, Object[] values) {
    Session session = null;

    try {
      session = HibernateUtil.getSessionFactory().openSession();

      // 创建条件容器并添加条件
      Criteria criteria = session.createCriteria(clazz);
      for (int i = 0; i < keys.length; i++) {
        criteria.add(Restrictions.eq(keys[i], values[i]));
      }

      // 统计行数
      int totalRows = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();      
      
      return totalRows;
    } catch (HibernateException e) {
      e.printStackTrace();
      return 0;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * 执行数据库操作
   * 
   * @param hql 操作语句
   * @param name 查询参数
   * @param val 参数值
   * @return  操作执行成功/失败
   */
  public static boolean dbExcute(String hql, String name, String val) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      
      // 创建查询语句并设置查询参数
      Query query = session.createSQLQuery(hql);
      query.setParameter(name, val);
      
      transaction = session.beginTransaction();
      transaction.begin();
      query.executeUpdate();
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
}