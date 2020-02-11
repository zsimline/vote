package org.vote.common;

import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.vote.common.HibernateUtil;
import org.vote.beans.Activity;
import org.vote.beans.User;
import org.vote.common.Code;

/**
 * 基础API类
 * 所有API类继承自此类
 */
public class BaseApi extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // json处理器
  private static final Gson gson = new Gson();

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response 响应对象
   * @param status   自定义的返回码
   * @throws ServletException
   * @throws IOException
   */
  protected void complete(HttpServletResponse response, int status) throws ServletException, IOException {
    Code code = new Code(status);
    String jsonStr = gson.toJson(code);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response 响应对象
   * @param status   自定义的返回码
   * @param extraStr 额外的说明
   * @throws ServletException
   * @throws IOException
   */
  protected void complete(HttpServletResponse response, int status, String extraStr) throws ServletException, IOException {
    Code code = new Code(status);
    code.setCodeDesc(extraStr);
    String jsonStr = gson.toJson(code);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 执行数据库操作
   * 
   * @param hql 操作语句
   * @param name 
   * @param val
   * @return  操作执行成功/失败
   */
  protected boolean dbExcute(String hql, String name, String val) {
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

  /**
   * 保存数据实例
   * 
   * @param instance 数据实例
   * @return 操作执行成功/失败
   */
  protected boolean saveInstance(Object instance) {
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
   * 保存或更新数据实例
   * 
   * @param instance 数据实例
   * @return 操作执行成功/失败
   */
  protected boolean saveOrUpdateInstance(Object instance) {
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
   * 更新数据库实例
   * 
   * @param instance
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
   * 根据ID获取实例
   * 
   * @param clazz 实例类
   * @param id 实例ID
   * @return
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
   * 识别并认证用户身份
   * 
   * @param request 请求对象
   * @param response 响应对象
   * @return 用户身份识别并认证成功返回其ID, 否则返回null
   */
  protected String userIdentify(HttpServletRequest request, HttpServletResponse response) {
    CookieFactory cookieFactory = new CookieFactory(request, response);
    HashMap<String, String> cookieMap = cookieFactory.cookiesToHashMap();

    // 判断用户是否携带认证信息
    String uid = cookieMap.get("uid");
    String token = cookieMap.get("token");
    if (uid == null || token == null) {
      return null;
    }

    try {
      // 根据UID获取用户实例并判断认证令牌是否相等
      User user = (User)getInstanceById(User.class, Long.valueOf(uid));
      if (user == null || !user.getToken().equals(token)) return null;
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return null;
    }

    return uid;
  }

  /**
   * 向用户发送JSON
   * 将对象转换为JSON字符串并发送
   * 
   * @param response 响应对象
   * @param obj 要转换的对象
   * @throws IOException
   */
  protected void sendJSON(HttpServletResponse response, Object obj) throws IOException {
    Gson gson = new Gson();
    String jsonStr = gson.toJson(obj);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 向用户发送JSON
   * 将对象转换为JSON字符串并发送
   * 
   * @param response 响应对象
   * @param objs 要转换的对象
   * @throws IOException
   */
  protected void sendJSON(HttpServletResponse response, List<?> objs) throws IOException {
    Gson gson = new Gson();
    String jsonStr = gson.toJson(objs);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 验证该用户是否有具有对某个活动的操作权限
   * 这些权限包括更新活动信息、更新报名信息、新增报名信息、
   * 审核报名信息、管理条目信息、结果与日志的查询
   * 当出现以下情况时该用户不可操作活动：
   * 用户未登录、活动不存在、活动不属于该用户
   * 
   * @param request 请求对象
   * @param response 响应对象
   * @return true/false 有/无权限
   * @throws ServletException
   * @throws IOException
   */
  protected boolean isMyActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    String uid = userIdentify(request, response);
    if (aid == null || uid == null) return false;
    Activity activity = (Activity)getInstanceById(Activity.class, aid);
    return activity != null && activity.getPublisher() == Long.valueOf(uid);
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
  protected List<?> paginationQuery(Class<?> clazz, String[] keys, Object[] values, int page, int max) {
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