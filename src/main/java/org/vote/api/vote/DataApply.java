package org.vote.api.vote;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseApi;
import org.vote.beans.Activity;

/**
 * 获取报名数据
 */
@WebServlet("/api/vote/data_apply")
public class DataApply extends BaseApi {
  private static final long serialVersionUID = 1L;

  public DataApply() {
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    
    if (!canFetch(aid, request, response)) {
      // 报名数据不可被获取时返回空数据
      sendJson(response, Collections.emptyList());
      return ;
    } else {  // 发送报名数据
      sendJson(response, fetchApplys(aid));
    }
  }

  /**
   * 获取报名数据
   * 
   * @param aid 活动ID
   * @return 报名数据
   */
  private List<?> fetchApplys(String aid) {
    String hql = "FROM Apply WHERE aid = :aid";
    String[] keys = {"aid"}, values = {aid};
    return getInstanceByHql(hql, keys, values);
  }

  /**
   * 验证报名数据是否可被获取
   * 当出现以下情况时报名数据不可被获取
   * - 用户未登录
   * - 活动不存在
   * - 活动不属于该用户
   * 
   * @param aid 活动ID 
   * @param request 请求对象
   * @param response 响应对象
   * @return true/false 数据可/不可被获取
   * @throws ServletException
   * @throws IOException
   */
  private boolean canFetch(String aid, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uid = userIdentify(request, response);
    Activity activity = (Activity)getInstanceById(Activity.class, aid);
    return uid != null && aid != null && activity.getPublisher() == Long.valueOf(uid);
  }
}
