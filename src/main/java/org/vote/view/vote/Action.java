package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;

/**
 * 显示投票页面
 */
@WebServlet("/vote/action")
public class Action extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    Activity activity = (Activity)getInstanceById(Activity.class, aid);
    request.setAttribute("aid", aid);
    request.setAttribute("activity", activity);
    display(request, response, "/template/vote/action.jsp");
  }
}
