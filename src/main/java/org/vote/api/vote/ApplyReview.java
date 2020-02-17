package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.beans.Apply;
import org.vote.beans.Entry;
import org.vote.common.BaseApi;

/**
 * 处理审核报名
 */
@WebServlet("/api/vote/apply/review")
public class ApplyReview extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ApplyReview() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Activity activity = (Activity) getInstanceById(Activity.class, request.getParameter("aid"));
    if (isMyActivity(request, activity)) {
      complete(response, 1602); return ;
    }
    
    char status = request.getParameter("status").charAt(0);
    switch (status) {
      case 'y': {
        if (handleApprove(request, activity)) {
          complete(response, 1600);
        } else {
          complete(response, 1601);
        };
        break;
      }
      case 'n': {
        if (handleReject(request)) {
          complete(response, 1600);
        } else {
          complete(response, 1601);
        }
        break;
      }
      default: {
        complete(response, 1602);
      }
    }
  }

  /**
   * 处理通过审核
   * 
   * @param request 请求对象
   * @return 操作成功/失败
   */
  private boolean handleApprove(HttpServletRequest request, Activity activity) {
    try {
      int id = Integer.valueOf(request.getParameter("id"));
      Apply apply = (Apply)getInstanceById(Apply.class, id);

      // 已通过审核，不可重复操作
      if (apply.getStatus() == 'y') return false;
      
      // 更新报名信息并同步数据到条目表
      apply.setStatus('y');
      Entry entry = createEntry(request, apply);

      // 设置条目的编号
      int sumEntry = activity.getSumEntry();
      entry.setNumber(sumEntry + 1);
      activity.setSumEntry(sumEntry + 1);

      return updateInstance(activity) && updateInstance(apply)
             && saveInstance(entry);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 处理拒绝审核
   * 
   * @param request 请求对象
   * @return 操作成功/失败
   */
  private boolean handleReject(HttpServletRequest request) {
    try {
      int id = Integer.valueOf(request.getParameter("id"));
      Apply apply = (Apply)getInstanceById(Apply.class, id);

      // 只能由待审核转换为未通过审核
      if (apply.getStatus() != 'w') return false;
      
      // 更新报名信息并同步数据到条目表
      apply.setStatus('n');
      return updateInstance(apply);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 创建条目
   * 
   * @param request 请求对象
   * @param apply 报名实例
   * @return 条目实例
   */
  private Entry createEntry(HttpServletRequest request, Apply apply) {
    Entry entry = new Entry();
    entry.setAid(request.getParameter("aid"));
    entry.setTitle(apply.getTitle());
    entry.setImgEntry(apply.getImgEntry());
    entry.setIntroduction(apply.getIntroduction());
    return entry;
  }
}