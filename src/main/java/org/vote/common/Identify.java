package org.vote.common;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.beans.User;
import org.vote.common.CookieFactory;
import org.vote.common.DBUtil;

/**
 * 用户身份处理器
 */
public class Identify {
  /**
   * 识别并认证用户身份
   * 
   * @param request 请求对象
   * @return 用户身份识别并认证成功返回其ID, 否则返回-1L
   */
  public static Long userIdentify(HttpServletRequest request) {
    HashMap<String, String> cookieMap = CookieFactory.cookiesToHashMap(request);

    // 获取Cookie认证信息
    String uid = cookieMap.get("uid");
    String token = cookieMap.get("token");

    // 未携带认证信息
    if (uid == null || token == null) return -1L;

    try {
      // 根据UID获取用户实例并判断认证令牌是否相等
      Long userId = Long.valueOf(uid);
      User user = (User) DBUtil.getInstanceById(User.class, userId);
      return user != null && user.getToken().equals(token) ? userId : -1L;
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return -1L;
    }
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