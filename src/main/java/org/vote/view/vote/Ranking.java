package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;
import org.vote.common.DBUtil;

/**
 * 显示排名页面
 */
@WebServlet("/vote/ranking")
public class Ranking extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Activity activity = (Activity) DBUtil.getInstanceById(Activity.class, request.getParameter("aid"));
    if (activity != null) {
      request.setAttribute("activity", activity);
      render(request, response, "/template/vote/ranking.jsp");
    } else {
      render404(response);
    }
  }
}