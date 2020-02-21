package org.vote.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;

/**
 * 用户身份拦截器
 * 匹配部分URL并拦截未登录用户
 */
public class PermissionInterceptor implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("身份拦截器初始化成功");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response =  (HttpServletResponse) servletResponse;
    long uid = Identify.userIdentify(request);

    if (uid == -1L) {  // 用户未登录
      response.sendRedirect("/user/login");
    } else {
      // 从数据库中获取用户请求的活动信息
      Activity activity = (Activity) DBUtil.getInstanceById(Activity.class, request.getParameter("aid"));
      if (activity == null) {  // 活动不存在
        response.sendRedirect("/index/error");
      } else if (uid != activity.getPublisher()) {  // 活动不属于该用户
        response.sendRedirect("/index");
      } else {  // 继续转发
        request.setAttribute("activity", activity);
        chain.doFilter(request, response);
      }
    }
  }

  @Override
  public void destroy() {
    System.out.println("身份拦截器器已销毁");
  }
}