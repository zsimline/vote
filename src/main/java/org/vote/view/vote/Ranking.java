package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;

/**
 * 显示创建投票页面
 */
@WebServlet("/vote/ranking")
public class Ranking extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    Activity activity = (Activity) getInstanceById(Activity.class, aid);
    if (activity == null) {
      response.sendRedirect("/index/error");
    } else {
      request.setAttribute("activity", activity);
      render(request, response, "/template/vote/ranking.jsp");
    }
  }
}