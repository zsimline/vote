package org.vote.view.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;
import org.vote.common.DBUtil;

/**
 * 显示活动管理页面
 */
@WebServlet("/admin/activity_manage")
public class ActivityManage extends BaseView {
  private static final long serialVersionUID = 1L;

  @SuppressWarnings("unchecked")
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int page = 1;
    try {
      page = Integer.valueOf(request.getParameter("page"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    int sumPages = (DBUtil.countRows(Activity.class) + 24) / 25;
    List<Activity> activitys =  (List<Activity>)DBUtil.paginationQuery(Activity.class, page, 25);
    request.setAttribute("page", page);
    request.setAttribute("activitys", activitys);
    request.setAttribute("sumPages", sumPages > 0 ? sumPages : 1);
    render(request, response, "/template/admin/activity_manage.jsp");
  }
}