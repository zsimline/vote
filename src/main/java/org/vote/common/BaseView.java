package org.vote.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.vote.common.HibernateUtil;

/**
 * 基础视图类
 * 所有视图类继承自此类
 */
public class BaseView extends BaseServlet {
  private static final long serialVersionUID = 1L;

  /**
   * 渲染视图
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
    *  渲染404页面
    *
    * @param response 响应对象
    * @throws IOException
   */
  protected void render404(HttpServletResponse response) throws IOException {
    response.sendRedirect("/index/error");
  }

  /**
    *  渲染404页面
    *
    * @param response 响应对象
    * @throws IOException
   */
  protected void renderIndex(HttpServletResponse response) throws IOException {
    response.sendRedirect("/index");
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