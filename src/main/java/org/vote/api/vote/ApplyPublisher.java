package org.vote.api.vote;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.api.user.Identify;
import org.vote.beans.Activity;
import org.vote.beans.Apply;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.common.Utils;

/**
 * 处理批量添加报名
 */
@WebServlet("/api/vote/apply/publisher")
public class ApplyPublisher extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ApplyPublisher() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    Activity activity = (Activity) DBUtil.getInstanceById(Activity.class, aid);
    Apply apply = (Apply) Utils.postDataToObj(request, Apply.class);

    if (activity == null || apply == null) {
      complete(response, 1401); return;
    } else if (!Identify.isMyActivity(request, activity)) {
      complete(response, 1404); return;
    } else if (!checkTime(activity)) {
      complete(response, 1405); return;
    } else {
      apply.setAid(aid);
      apply.setStatus('y');
    }
    
    // 执行数据存储
    if (DBUtil.saveInstance(apply)) {
      sendJSON(response, apply);
    } else {
      complete(response, 1401);
    }
  }

  /**
   * 检测投票时间
   * 判断是否在报名时间区间内
   * 
   * @param activity 活动实例
   * @return 在时间区间内返回true, 否则返回false
   */
  private boolean checkTime(Activity activity) {
    Date voteTimeEnd  = activity.getVoteTimeEnd();
    Date  currentTime =  new Date();
    return voteTimeEnd.after(currentTime);
  }
}