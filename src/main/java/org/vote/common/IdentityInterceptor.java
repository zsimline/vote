package org.vote.common;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户身份拦截器
 * 匹配部分URL并拦截未登录用户
 */
public class IdentityInterceptor implements Filter {
  private List<String> adminRoutes = Arrays.asList(
    "/admin/user_manage",
    "/admin/activity_manage"
  );

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("身份拦截器初始化成功");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response =  (HttpServletResponse) servletResponse;
    long uid = Identify.userIdentify(request);

    if (uid == -1L) {
      String redirectUrl = "/user/login?referer=%s";
      response.sendRedirect(String.format(redirectUrl, request.getRequestURI()));
    } else {
      boolean isAdminRoute = adminRoutes.contains(request.getRequestURI());
      boolean noPrivilege = !(boolean)request.getSession().getAttribute("admin");
      if ( isAdminRoute && noPrivilege) {  // 普通用户无权访问管理员页面
        String redirectUrl = "/user/login?referer=%s";
        response.sendRedirect(String.format(redirectUrl, request.getRequestURI()));
      } else {
        request.setAttribute("uid", uid);
        chain.doFilter(request, response);
      }
    }
  }

  @Override
  public void destroy() {
    System.out.println("身份拦截器器已销毁");
  }
}