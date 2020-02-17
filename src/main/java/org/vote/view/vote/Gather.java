package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;

/**
 * 显示汇总页面
 */
@WebServlet("/vote/gather")
public class Gather extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("activity",  (Activity) request.getAttribute("activity"));
    render(request, response, "/template/vote/gather.jsp");
  }
}