package org.vote.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import org.vote.beans.User;
import org.vote.common.Code;
import org.vote.common.HibernateUtil;
import org.vote.common.MD5;
import org.vote.common.Utils;
import org.vote.common.Email;

/**
 * 处理创建活动
 */
@WebServlet("/api/register")
public class Register extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Register() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String jsonStr = Utils.inputToJsonStr(request);

    if (jsonStr != null) {
      Gson gson = new Gson();
      User user = gson.fromJson(jsonStr, User.class);

      // 取用户密码的哈希摘要
      try {
        user.setPassword(MD5.md5(user.getPassword()));
      } catch (Exception e) {
        e.printStackTrace();
        completed(response, 1103);
      }

      if (dbExcute(user)) {
        verifyEmail(response, user);
        completed(response, 1100);
      } else {
        completed(response, 1101);
      }
    } else {
      completed(response, 1102);
    }
  }

  /**
   * 向用户邮箱发送验证信息
   * 
   * @param response Servlet 响应对象
   * @param user 用户实例
   * @throws IOException
   * @throws ServletException
   */
  private void verifyEmail(HttpServletResponse response, User user) throws ServletException, IOException {
    String emailAddress = user.getEmail();
    String mailContent = "<a href=\"http://127.0.0.1:8080/vote/activation.jsp?"
                         + "email=" + emailAddress + "&code=" + user.getPassword()
                         + "\">点击链接激活账户</a>";

    if (!Email.sendMail(emailAddress, mailContent)) {
      completed(response, 1104);
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
   * 执行新建用户的数据库操作
   * 
   * @param user 用户实例
   * @return true/false 新建用户成功/失败
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
