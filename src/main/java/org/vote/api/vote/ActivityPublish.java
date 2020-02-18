package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.api.user.Identify;
import org.vote.beans.Activity;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.common.UUIDTool;
import org.vote.common.Utils;

/**
 * 处理创建活动
 */
@WebServlet("/api/vote/activity/publish")
public class ActivityPublish extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ActivityPublish() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    long uid = Identify.userIdentify(request);
    Activity activity = (Activity) Utils.postDataToObj(request, Activity.class);

    // 验证权限与数据完整性
    if (uid == -1L) {
      complete(response, 1002); return ;
    } else if (activity == null) {
      complete(response, 1003); return ;
    } else {
      // 生成活动的ID
      activity.setId(UUIDTool.getUUID());
      
      // 设置发布者ID
      activity.setPublisher(uid);
    }

    // 执行数据存储
    if (createTicketTable(activity.getId()) && DBUtil.saveInstance(activity)) {
      complete(response, 1000);
    } else {
      complete(response, 1001);
    }
  }

  /**
   * 创建投票表
   * 
   * @param aid 活动ID
   * @return 创建投票表成功/失败
   */
  private boolean createTicketTable(String aid) {
    // 调用创建投票表事务
    String hql = "call create_ticket_table(:uuid)";
    return DBUtil.dbExcute(hql, "uuid", aid);
  }
}