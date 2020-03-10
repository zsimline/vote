package org.vote.api.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.beans.Activity;

/**
 * 处理删除活动
 */
@WebServlet("/api/admin/activity_delete")
public class ActivityDelete extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ActivityDelete() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (!(boolean)request.getSession().getAttribute("admin")) complete(response, 5002);
    
    String aid = request.getParameter("aid");
    Activity activity = (Activity) DBUtil.getInstanceById(Activity.class, aid);

    // 设置活动为销毁
    // 删除投票表并更新活动实例
    activity.setDestroyed(true);
    if (deleteTicketTable(aid) && DBUtil.updateInstance(activity)) {
      complete(response, 5000);
    } else {
      complete(response, 5001);
    }
  }

  /**
   * 删除投票表
   * 
   * @param aid 活动ID
   * @return 删除投票表成功/失败
   */
  private boolean deleteTicketTable(String aid) {
    // 调用删除投票表事务
    String hql = "call drop_ticket_table(:uuid)";
    return DBUtil.dbExcute(hql, "uuid", aid);
  }
}