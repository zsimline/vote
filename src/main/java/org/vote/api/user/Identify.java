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
      User user = getInstanceById(User.class, userId);
      if (!user.getToken().equals(token)) {
        User user2 = getInstanceById(User.class, userId);
        System.out.println(user2.getToken());
      }

      return user != null && user.getToken().equals(token) ? userId : -1L;
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return -1L;
    }
  }

  /**
   * 根据ID获取实例
   * 
   * @param clazz 实例类
   * @param id 实例ID
   * @return
   */
  public static User getInstanceById(Class<?> clazz, long id) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      return (User) session.get(clazz, id);
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