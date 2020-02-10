package org.vote.view.vote;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;

/**
 * 显示管理投票页面
 */
@WebServlet("/vote/manage")
public class Manage extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    long uid;
    try {
      uid = Long.valueOf(userIdentify(request, response));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      response.sendRedirect("/user/login");
      return ;
    }

    List<Activity> activitys = getActivitysByPublisher(uid);
    request.setAttribute("activitys", activitys);
    render(request, response, "/template/vote/manage.jsp");
  }

  /**
   * 根据发布者获取活动实例列表
   * 
   * @param uid 发布者UID
   * @return 活动实例列表
   */
  @SuppressWarnings("unchecked")
  private List<Activity> getActivitysByPublisher(long uid) {
    List<?>  activitys =  conditionQuery(Activity.class, "publisher", uid);   
    return (List<Activity>) activitys;
  }
}