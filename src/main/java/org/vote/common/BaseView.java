package org.vote.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.vote.common.HibernateUtil;

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
}
