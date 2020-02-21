package org.vote.api.gather;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.vote.beans.Entry;
import org.vote.common.BaseApi;
import org.vote.common.HibernateUtil;

@WebServlet("/api/vote/gather/ranking")
public class Ranking extends BaseApi {
  private static final long serialVersionUID = 1L;

  // 当次最大获取数量
  private static final int max = 50;

  public Ranking() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    int page = 1;
    try {
      page = Integer.valueOf(request.getParameter("page"));
    } catch (NumberFormatException e) {
      e.printStackTrace(); 
    }

    sendJSON(response,  getRankingData(aid, page));
  }

  /**
   * 获取排名数据
   * 
   * @param aid 活动id
   * @param page 当前页码
   * @return 排名数据列表
   */
  private List<?> getRankingData(String aid, int page) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();

      // 创建条件容器并附加条件
      Criteria criteria = session.createCriteria(Entry.class); 
      criteria.add(Restrictions.eq("aid", aid));
      criteria.add(Restrictions.eq("isFreeze", false));
      criteria.addOrder(Order.desc("acquisition"));

      // 设置单次最大获取数量
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
}