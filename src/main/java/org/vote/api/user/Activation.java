package org.vote.api.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import org.vote.beans.User;
import org.vote.common.Code;
import org.vote.common.HibernateUtil;


/**
 * 处理激活账户
 */
@WebServlet("/api/user/activation")
public class Activation extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Activation() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String emailAddress = request.getParameter("email");
    String password = request.getParameter("code");

    User user = getUserByEmail(emailAddress);
    if (user == null) {
      completed(response, 1302);
    }
    if (!user.getPassword().equals(password)) {
      completed(response, 1303);
      return ;
    }

    // 设置账户为激活的
    user.setIsActive(true);
    
    if (dbExcute(user)) {
      completed(response, 1300);
    } else {
      completed(response, 1301);
    }
  }

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response Servlet响应对象
   * @param status 自定义的返回码
   * @throws ServletException
   * @throws IOException
   */
  private void completed(HttpServletResponse response, int status) throws ServletException, IOException {
    Code code = new Code(status);
    Gson gson = new Gson();
    String jsonObj = gson.toJson(code);
    response.getWriter().write(jsonObj);
    response.setStatus(200);
  }

  /**
   * 根据邮件地址获取用户
   * 
   * @param emailAddress 邮件地址
   * @return 用户实例
   */
  @SuppressWarnings("unchecked")
  private User getUserByEmail(String emailAddress) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      
      String hql = "FROM User WHERE email = :emailAddress";
      Query query = session.createQuery(hql);
      query.setParameter("emailAddress", emailAddress);
      List<User> results = (List<User>) query.list();
      
      transaction.commit();
      session.close();
      
      return results.isEmpty() ? null :results.get(0);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 执行用户激活的数据库操作
   * 
   * @param activity 活动实例
   * @return true/false 新建活动成功/失败
   */
  private boolean dbExcute(User user) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      session.update(user);
      transaction.commit();
      session.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
