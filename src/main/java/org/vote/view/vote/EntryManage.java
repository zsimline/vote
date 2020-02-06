package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Entry;
import org.vote.common.BaseView;

/**
 * 显示创建投票页面
 */
@WebServlet("/vote/entry_manage")
public class EntryManage extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("aid", request.getParameter("aid"));
    request.setAttribute("page", request.getParameter("page"));
    int sumPages = getSumPages(request);
    request.setAttribute("sumPages", sumPages > 0 ? sumPages : 1);    
    display(request, response, "/template/vote/entry_manage.jsp");
  }

  private int getSumPages(HttpServletRequest request) {
    String aid = request.getParameter("aid");
    String[] keys = { "aid" };
    Object[] values = { aid };
    return (countRows(Entry.class, keys, values) + 14) / 15;
  }
}