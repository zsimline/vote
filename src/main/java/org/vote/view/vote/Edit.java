package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;

/**
 * 显示编辑报名页面
 */
@WebServlet("/vote/edit")
public class Edit extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Activity activity = (Activity) request.getAttribute("activity");
    request.setAttribute("activity", activity);
    request.setAttribute("options", activity.getOptions().split(","));
    render(request, response, "/template/vote/edit.jsp");
  }
}