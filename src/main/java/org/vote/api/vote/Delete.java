package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.vote.common.HibernateUtil;

import org.vote.common.Code;
import org.vote.beans.Activity;

import com.google.gson.Gson;

/**
 * 处理删除活动
 */
@WebServlet("/api/delete")
public class Delete extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Delete() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    if (dbExcute(aid) > 0) {
      completed(response, 1900);
    } else {
      completed(response, 1901);
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
   * 执行删除活动的数据库操作
   * @return 影响的行数
   */
  private int dbExcute(String aid) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      
      Activity activity = (Activity) session.get(Activity.class, aid);
      System.out.println(activity);
      activity.setDestroyed(true);
      session.update(activity);

      // 删除投票表
      SQLQuery query = session.createSQLQuery("call drop_ticket_table(:uuid)");
      query.setParameter("uuid", activity.getId());
      query.executeUpdate();

      transaction.commit();
      session.close();
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }

    return 1;
  }
}
