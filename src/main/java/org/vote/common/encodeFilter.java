package org.vote.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 编码过滤器
 */
public class encodeFilter implements Filter {
  private FilterConfig filterConfig;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    System.out.println("编码过滤器初始化成功");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // 设置Servlet请求与响应的编码
    request.setCharacterEncoding(filterConfig.getInitParameter("encoding"));
    response.setContentType("application/json;charset=utf-8");

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    System.out.println("编码过滤器已销毁");
  }
}
