package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseView;
import org.vote.beans.Activity;

/**
 * 显示报名页面
 */
@WebServlet("/vote/apply")
public class Apply extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    Activity activity = (Activity)getInstanceById(Activity.class, aid);
    if (activity != null && activity.getExternalApply()) {
      request.setAttribute("aid", aid);
      request.setAttribute("options", activity.getOptions().split(","));
      render(request, response, "/template/vote/apply.jsp");
    } else {
      response.sendRedirect("/index/error");
    }
  }
}