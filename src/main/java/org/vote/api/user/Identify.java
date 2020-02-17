package org.vote.api.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.vote.beans.User;
import org.vote.common.CookieFactory;
import org.vote.common.HibernateUtil;

/**
 * 用户身份处理器
 */
public class Identify {
  /**
   * 识别并认证用户身份
   * 
   * @param request 请求对象
   * @param response 响应对象
   * @return 用户身份识别并认证成功返回其ID, 否则返回-1L
   */
  public static Long userIdentify(HttpServletRequest request, HttpServletResponse response) {
    CookieFactory cookieFactory = new CookieFactory(request, response);
    HashMap<String, String> cookieMap = cookieFactory.cookiesToHashMap();

    // 获取Cookie认证信息
    String uid = cookieMap.get("uid");
    String token = cookieMap.get("token");

    // 未携带认证信息
    if (uid == null || token == null) return -1L;

    try {
      // 根据UID获取用户实例并判断认证令牌是否相等
      Long userId = Long.valueOf(uid);
      User user = getUserById(userId);
      return user != null && user.getToken().equals(token) ? userId : -1L;
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return -1L;
    }
  }

  /**
   * 获取用户数据
   * 
   * @param id UID
   * @return 用户实例
   */
  public static User getUserById(long id) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      return (User) session.get(User.class, id);
    } catch (HibernateException e) {
      e.printStackTrace();
      return null;
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
}