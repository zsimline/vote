package org.vote.api.vote;

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
import org.hibernate.criterion.Restrictions;
import org.vote.beans.Entry;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.common.HibernateUtil;

/**
 * 处理审核报名
 */
@WebServlet("/api/vote/entry/search")
public class EntrySearch extends BaseApi {
  private static final long serialVersionUID = 1L;

  public EntrySearch() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String searchType = request.getParameter("type");

    if (searchType == null) {
      sendJSON(response, Collections.emptyList());
    } else if (searchType.equals("number")) {
      sendJSON(response, searchEntryByNumber(request));
    } else if (searchType.equals("title")) {
      sendJSON(response, searchEntryByTitle(request));
    } else {
      sendJSON(response, Collections.emptyList());
    }
  }

  private List<?> searchEntryByNumber(HttpServletRequest request) {
    int number = 0; 
    try {
      number = Integer.valueOf(request.getParameter("content"));
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }

    String[] keys = {"aid", "number"};
    Object[] values = {request.getParameter("aid"), number};

    return DBUtil.conditionQuery(Entry.class, keys, values);
  }

  private List<?> searchEntryByTitle(HttpServletRequest request) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();

      // 创建条件容器并附加条件
      Criteria criteria = session.createCriteria(Entry.class);  
      criteria.add(Restrictions.eq("aid", request.getParameter("aid")));
      criteria.add(Restrictions. like("title", "%" + request.getParameter("content") + "%"));

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