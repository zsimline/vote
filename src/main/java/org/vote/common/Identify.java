package org.vote.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.DBUtil;

/**
 * 用户身份处理器
 */
public class Identify {
  /**
   * 识别用户身份
   * 
   * @param request 请求对象
   * @return 身份识别成功返回其ID, 否则返回-1L
   */
  public static Long userIdentify(HttpServletRequest request) {
    // 从Session中获取用户登录状态
    Object uid = request.getSession().getAttribute("uid");
    System.out.println(request.getSession().getMaxInactiveInterval());
    System.out.println(request.getSession().getId());
    System.out.println(request.getSession().getLastAccessedTime());
    return uid == null ? -1L :  (long) uid;
  }

  /**
   * 验证活动是否属于该用户
   * 当出现以下情况时该用户不可操作活动：
   * 用户未登录、活动不存在、活动不属于该用户
   * 
   * @param request 请求对象
   * @param response 响应对象
   * @return  活动属于该用户返回true，否则返回false
   */
  public static boolean isMyActivity(HttpServletRequest request, HttpServletResponse response) {
    String aid = request.getParameter("aid");
    long uid  = userIdentify(request);
    Activity activity = (Activity) DBUtil.getInstanceById(Activity.class, aid);
    return activity != null && activity.getPublisher() == uid ? true : false;
  }

  /**
   * 验证活动是否属于该用户
   * 
   * @param request 请求对象
   * @param activity 活动实例
   * @return  活动属于该用户返回true，否则返回false
   */
  public static boolean isMyActivity(HttpServletRequest request, Activity activity) {
    long uid  = userIdentify(request);
    return activity  != null && activity.getPublisher() == uid ? true : false;
  }
}