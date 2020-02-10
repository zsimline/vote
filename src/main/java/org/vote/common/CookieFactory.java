package org.vote.common;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie处理器
 */
public class CookieFactory {
  // 请求对象
  HttpServletRequest request;

  // 响应对象
  HttpServletResponse response;

  public CookieFactory(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }

  /**
   * 设置cookie
   * 过期时间与路径为默认
   * 
   * @param name
   * @param value
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

  /**
   * cookie转换为哈希表
   * 
   * @return 转换后的哈希表
   */
  public HashMap<String, String> cookiesToHashMap() {
    HashMap<String, String> cookieMap = new HashMap<String, String>();
    Cookie[] cookies = request.getCookies();
    
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        cookieMap.put(cookie.getName(), cookie.getValue());
      }
    }
    return cookieMap;
  }
}