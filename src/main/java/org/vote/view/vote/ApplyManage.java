package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.beans.Apply;
import org.vote.common.BaseView;

/**
 * 显示报名管理页面
 */
@WebServlet("/vote/apply_manage")
public class ApplyManage extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    Activity activity = (Activity)getInstanceById(Activity.class, aid);
    int sumPages = getSumPages(request);

    request.setAttribute("aid", aid);
    request.setAttribute("options", activity.getOptions().split(","));
    request.setAttribute("status", request.getParameter("status"));
    request.setAttribute("page", request.getParameter("page"));
    request.setAttribute("sumPages", sumPages > 0 ? sumPages : 1);


    display(request, response, "/template/vote/apply_manage.jsp");
  }

  private int getSumPages(HttpServletRequest request) {
    String aid = request.getParameter("aid");
    char status = request.getParameter("status").charAt(0);
    String[] keys = { "aid", "status" };
    Object[] values = { aid, status };
    return (countRows(Apply.class, keys, values) + 14) / 15;
  }
}
