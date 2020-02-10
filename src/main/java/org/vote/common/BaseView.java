package org.vote.common;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.vote.beans.User;
import org.vote.common.HibernateUtil;
import org.vote.common.CookieFactory;

/**
 * 基础视图类
 * 所有视图类继承自此类
 */
public class BaseView extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * 显示视图
   * 将请求转发到相应的模板
   * 
   * @param request 请求对象
   * @param response 响应对象 
   * @param path 视图模板路径
   * @throws ServletException
   * @throws IOException
   */
  protected void render(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
    RequestDispatcher rd = request.getRequestDispatcher(path);
    rd.forward(request, response);
  }
 
  /**
   * 根据ID获取实例
   * 
   * @param clazz 实例类
   * @param id 实例ID
   * @return 实例
   */
  protected Object getInstanceById(Class<?> clazz, String id) {
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
  protected Object getInstanceById(Class<?> clazz, long id) {
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
   * 识别用户身份并认证
   * 
   * @param request 请求对象
   * @return 用户身份识别并认证成功返回其ID, 否则返回null
   */
  protected String userIdentify(HttpServletRequest request, HttpServletResponse response) {
    CookieFactory cookieFactory = new CookieFactory(request, response);
    HashMap<String, String> cookieMap = cookieFactory.cookiesToHashMap();

    String uid = cookieMap.get("uid");
    String token = cookieMap.get("token");
    if (uid == null || token == null) {
      return null;
    }

    // 根据UID获取用户实例并判断认证令牌是否相等
    User user = (User) getInstanceById(User.class, Long.valueOf(uid));
    if (user == null || !user.getToken().equals(token)) {
      return null;
    }

    return uid;
  }

  /**
   * 统计行数
   * 
   * @param clazz 实例类
   * @return 行数
   */
  protected int countRows(Class<?> clazz) {
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
  
  /**
   * 按条件统计行数
   * 
   * @param clazz  实例类
   * @param keys   条件名集合
   * @param values 条件值集合
   * @return 行数
   */
  protected int countRows(Class<?> clazz, String[] keys, Object[] values) {
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
}