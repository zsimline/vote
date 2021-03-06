package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.common.Identify;
import org.vote.beans.Activity;

/**
 * 处理删除活动
 */
@WebServlet("/api/vote/activity/remove")
public class ActivityRemove extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ActivityRemove() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    Activity activity = (Activity) DBUtil.getInstanceById(Activity.class, aid);
    long uid = Identify.userIdentify(request);
    
    // 验证是否可删除
    if (!canDelete(uid, activity)) {
      complete(response, 1902); return ;
    }

    // 设置活动为销毁
    // 删除投票表并更新活动实例
    activity.setDestroyed(true);
    if (deleteTicketTable(aid) && DBUtil.updateInstance(activity)) {
      complete(response, 1900);
    } else {
      complete(response, 1901);
    }
  }

  /**
   * 验证投票是否可被删除
   * 当出现以下情况时投票不可被删除
   * - 用户未登录
   * - 活动不存在
   * - 活动不属于该用户
   * 
   * @param uid 活动ID
   * @param activity 活动实例
   * @return true/false 投票可/不可被删除
   */
  private boolean canDelete(long uid, Activity activity) {
    return uid != -1L && activity != null && activity.getPublisher() == uid;
  }

  /**
   * 创建投票表
   * 
   * @param aid 活动ID
   * @return 创建投票表成功/失败
   */
  private boolean deleteTicketTable(String aid) {
    // 调用删除投票表事务
    String hql = "call drop_ticket_table(:uuid)";
    return DBUtil.dbExcute(hql, "uuid", aid);
  }
}