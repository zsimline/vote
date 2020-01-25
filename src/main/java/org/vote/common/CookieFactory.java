package org.vote.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie处理器
 */
public class CookieFactory {
  // Servlet 响应对象
  HttpServletResponse response;

  public CookieFactory(HttpServletResponse response) {
    this.response = response;
  }

  /**
   * 设置cookie
   * 过期时间与路径为默认
   * 
   * @param name
   * @param value
   * @param expire
   * @param path
   */
  public void setCookie(String name, String value) {
    Cookie cookie = new Cookie(name, value);
    cookie.setMaxAge(604800);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  /**
   * 设置cookie
   * 过期时间与路径由用户给出
   * 
   * @param name
   * @param value
   * @param expire
   * @param path
   */
  public void setCookie(String name, String value, int expiry, String path) {
    Cookie cookie = new Cookie(name, value);
    cookie.setMaxAge(expiry);
    cookie.setPath(path);
    response.addCookie(cookie);
  }
}
