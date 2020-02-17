package org.vote.view.vote;

import java.io.IOException;
import java.util.Date;

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
    Activity activity = (Activity)getInstanceById(Activity.class, request.getParameter("aid"));
    if (activity != null && activity.getExternalApply()) {
      request.setAttribute("activity", activity);
      request.setAttribute("applyTimeStatus", checkTime(activity));
      request.setAttribute("options", activity.getOptions().split(","));
      render(request, response, "/template/vote/apply.jsp");
    } else {
      render404(response);
    }
  }
  
  /**
   * 检测报名时间
   * 
   * @param activity 活动实例
   * @return -1 报名未开始 1 报名已截止 0 可正常报名
   */
  private int checkTime(Activity activity) {
    Date applyTimeStart = activity.getApplyTimeStart();
    Date applyTimeEnd  = activity.getApplyTimeEnd();
    Date  currentTime = new Date();
    return applyTimeStart.after(currentTime) ? -1 : (applyTimeEnd.before(currentTime) ? 1 : 0);
  }
}