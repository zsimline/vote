package org.vote.processor.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import org.vote.beans.User;
import org.vote.common.Code;
import org.vote.common.HibernateUtil;
import org.vote.common.MD5;

/**
 * 处理创建活动
 */
@WebServlet("/v2/register")
public class Register extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Register() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 将输入流转换为JSON字符串
    String postData = IOUtils.toString(request.getInputStream(), "UTF-8");

    if (postData != null) {
      Gson gson = new Gson();
      User user = gson.fromJson(postData, User.class);

      // 取用户密码的哈希摘要
      try {
        user.setPassword(MD5.md5(user.getPassword()));
      } catch (Exception e) {
        e.printStackTrace();
        completed(response, 1103);
      }
      
      if (dbExcute(user)) {
        completed(response, 1100);
      } else {
        completed(response, 1101);
      }
    } else {
      completed(response, 1102);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  /**
   * 向用户返回操作执行的结果
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
   * 执行新建活动的数据库操作
   * @param activity 活动实例
   * @return true/false 新建活动成功/失败
   */
  private boolean dbExcute(User user) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      session.save(user);
      transaction.commit();
      session.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
