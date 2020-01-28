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
    String uid = userIdentify(request, response);
    if (uid == null) {
      response.sendRedirect("/user/login");
    } else {
      List<Activity> activitys = getActivitysByPublisher(uid);
      request.setAttribute("activitys", activitys);
      display(request, response, "/template/vote/manage.jsp");
    }
  }

  /**
   * 根据发布者获取活动实例列表
   * 
   * @param uid 发布者UID
   * @return 活动实例列表
   */
  @SuppressWarnings("unchecked")
  private List<Activity> getActivitysByPublisher(String uid) {
    String hql = "FROM Activity WHERE publisher = :publisher AND destroyed=0";
    String[] keys = {"publisher"};
    long[] values = {Long.valueOf(uid)};
    
    List<Activity> activitys = (List<Activity>)getInstanceByHql(hql, keys, values);
    
    return activitys;
  }
}
