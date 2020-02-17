package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseApi;
import org.vote.common.Utils;

/**
 * 处理创建活动
 */
@WebServlet("/api/vote/activity/update")
public class ActivityUpdate extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ActivityUpdate() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Activity activityOld = (Activity) getInstanceById(Activity.class, request.getParameter("aid"));
    Activity activityNew = (Activity) Utils.postDataToObj(request, Activity.class);

    // 验证权限与数据完整性
    if (activityOld == null) {
      complete(response, 1704); return ;
    } else if (activityNew == null) {
      complete(response, 1703); return ;
    } else if (!isMyActivity(request, activityOld)) {
      complete(response, 1702); return ;
    } else {
      syncActivityData(activityOld, activityNew);
    }

    // 执行数据存储
    if (updateInstance(activityNew)) {
      complete(response, 1700);
    } else {
      complete(response, 1701);
    }
  }

  /**
   * 将必要的数据从旧活动实例
   * 同步到新活动实例
   * 
   * @param activityOld 旧的活动实例
   * @param activityNew 新的活动实例
   */
  private void syncActivityData(Activity activityOld, Activity activityNew) {
    activityNew.setId(activityOld.getId());
    activityNew.setPublisher(activityOld.getPublisher());
    activityNew.setSumEntry(activityOld.getSumEntry());
    activityNew.setSumVoted(activityOld.getSumVoted());
    activityNew.setSumVisited(activityOld.getSumVisited());
    activityNew.setDestroyed(activityOld.getDestroyed());
    if (activityNew.getImgMain() == null) {
      activityNew.setImgMain(activityOld.getImgMain());
    }
  }
}