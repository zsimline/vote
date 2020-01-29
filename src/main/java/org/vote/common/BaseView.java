package org.vote.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
  protected void display(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
    RequestDispatcher rd = request.getRequestDispatcher(path);
    rd.forward(request, response);
  }
 
  /**
   * 根据ID获取实例
   * 
   * @param clazz 实例类
   * @param id 实例ID
   * @return
   */
  protected Object getInstanceById(Class<?> clazz, String id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      Object instance = session.get(clazz, id);
      transaction.commit();
      session.close();
      return instance;
    } catch (HibernateException e) {
      e.printStackTrace();
      return null;
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
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      Object instance = session.get(clazz, id);
      transaction.commit();
      session.close();
      return instance;
    } catch (HibernateException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 根据查询语句获取数据实例列表
   * 
   * @param hql 查询语句
   * @param keys 查询语句参数集合
   * @param values 查询语句参数值集合
   * @return 数据实例集合
   */
  protected List<?> getInstanceByHql(String hql, String[] keys, String[] values) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      
      // 创建查询语句并设置查询参数
      Query query = session.createQuery(hql);
      for (int i = 0; i < keys.length; i++) {
        query.setParameter(keys[i], values[i]);
      }
  
      List<?> results = query.list();
      
      transaction.commit();
      session.close();

      return results.isEmpty() ? null : results;
    } catch (HibernateException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 根据查询语句获取数据实例列表
   * 
   * @param hql 查询语句
   * @param keys 查询语句参数集合
   * @param values 查询语句参数值集合
   * @return 数据实例集合
   */
  protected List<?> getInstanceByHql(String hql, String[] keys, long[] values) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      
      // 创建查询语句并设置查询参数
      Query query = session.createQuery(hql);
      for (int i = 0; i < keys.length; i++) {
        query.setParameter(keys[i], values[i]);
      }
  
      List<?> results = query.list();
      
      transaction.commit();
      session.close();

      return results.isEmpty() ? null : results;
    } catch (HibernateException e) {
      e.printStackTrace();
      return null;
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
    User user = (User)getInstanceById(User.class, Long.valueOf(uid));
    if (user == null || !user.getToken().equals(token)) {
      return null;
    }

    return uid;
  }
}
