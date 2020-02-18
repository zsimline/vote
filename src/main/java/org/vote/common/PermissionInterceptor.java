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

import org.vote.common.Identify;
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
    Activity activity = (Activity) DBUtil.getInstanceById(Activity.class, request.getParameter("aid"));
    
    if (activity == null) {
      response.sendRedirect("/index/error");
    } else if (!Identify.isMyActivity(request, activity)) {
      response.sendRedirect("/index");
    } else {
      request.setAttribute("activity", activity);
      chain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {
    System.out.println("身份拦截器器已销毁");
  }
}