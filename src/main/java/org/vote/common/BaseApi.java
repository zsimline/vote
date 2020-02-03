package org.vote.common;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.vote.common.HibernateUtil;
import org.vote.beans.User;
import org.vote.common.Code;

/**
 * 基础API类
 * 所有API类继承自此类
 */
public class BaseApi extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response 响应对象
   * @param status   自定义的返回码
   * @throws ServletException
   * @throws IOException
   */
  protected void completed(HttpServletResponse response, int status) throws ServletException, IOException {
    Code code = new Code(status);
    Gson gson = new Gson();
    String jsonStr = gson.toJson(code);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response 响应对象
   * @param status   自定义的返回码
   * @param extraStr 额外发送的字符串
   * @throws ServletException
   * @throws IOException
   */
  protected void completed(HttpServletResponse response, int status, String extraStr) throws ServletException, IOException {
    Code code = new Code(status);
    code.setExtraStr(extraStr);
    Gson gson = new Gson();
    String jsonStr = gson.toJson(code);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 执行数据库操作
   * 
   * @param hql 查询语句
   * @param keys 查询语句参数集合
   * @param values 查询语句参数值集合
   * @return true/false 执行数据操作成功/失败
   */
  protected boolean dbExcute(String hql, String[] keys, String[] values) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();

      // 创建查询语句并设置查询参数
      Query query = session.createSQLQuery(hql);
      for (int i = 0; i < keys.length; i++) {
        query.setParameter(keys[i], values[i]);
      }
      query.executeUpdate();

      transaction.commit();
      session.close();
    } catch (HibernateException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * 保存数据实例
   * 
   * @param instance
   * @return 保存数据实例成功/失败
   */
  protected boolean saveInstance(Object instance) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      session.save(instance);
      transaction.commit();
      session.close();
    } catch (HibernateException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * 更新数据库实例
   * 
   * @param instance
   * @return 更新数据实例成功/失败
   */
  protected boolean updateInstance(Object instance) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      session.update(instance);
      transaction.commit();
      session.close();
    } catch (HibernateException e) {
      e.printStackTrace();
      return false;
    }

    return true;
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

  /**
   * 发送JSON数据
   * 将对象转换为JSON字符串并发送
   * 
   * @param response 响应对象
   * @param objs 要转换的对象
   * @throws IOException
   */
  protected void sendJson(HttpServletResponse response, List<?> objs) throws IOException {
    Gson gson = new Gson();
    String jsonStr = gson.toJson(objs);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }
}
