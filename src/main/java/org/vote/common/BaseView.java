package org.vote.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * 基础视图类
 * 所有视图类继承自此类
 */
public class BaseView extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * 渲染视图
   * 将请求转发到相应的模板
   * 
   * @param request 请求对象
   * @param response 响应对象 
   * @param path 视图模板路径
   * @throws ServletException
   * @throws IOException
   */
  protected void render(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
    RequestDispatcher rd = request.getRequestDispatcher(path);
    rd.forward(request, response);
  }

  /**
    *  渲染404页面
    *
    * @param response 响应对象
    * @throws IOException
   */
  protected void render404(HttpServletResponse response) throws IOException {
    response.sendRedirect("/index/error");
  }
}