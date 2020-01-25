package org.vote.processor.user;

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
import org.vote.common.MD5;
import org.vote.common.UUIDTool;
import org.vote.common.CookieFactory;
import org.vote.common.Utils;

/**
 * 处理登录账户
 */
@WebServlet("/v2/login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Login() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String jsonStr = Utils.inputToJsonStr(request);

    if (jsonStr != null) {
      Gson gson = new Gson();
      User userVerify = gson.fromJson(jsonStr, User.class);
      User user = getUserByEmail(userVerify.getEmail());

      // 验证账户是够存在
      if (user == null) {
        completed(response, 1202);
        return;
      }

      // 验证账户是否是已激活的
      if (user.getIsActive() == false) {
        completed(response, 1204);
        return;
      }

      try {
        if (MD5.verify(userVerify.getPassword(), user.getPassword())) {
          // 生成登录令牌
          String token = UUIDTool.getUUID();
          user.setToken(token);

          if (dbExcute(user)) {
            CookieFactory cookieFactory = new CookieFactory(response);
            cookieFactory.setCookie("uid", String.valueOf(user.getId()));
            cookieFactory.setCookie("token", token);
            completed(response, 1200);
          } else {
            completed(response, 1205);
          }
        } else {
          completed(response, 1203);
        }
      } catch (Exception e) {
        e.printStackTrace();
        completed(response, 1201);
      }
    }
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

      return results.isEmpty() ? null : results.get(0);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response Servlet响应对象
   * @param status   自定义的返回码
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
   * 执行更新登录令牌的数据库操作
   * 
   * @param activity 活动实例
   * @return true/false 更新登录令牌成功/失败
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
