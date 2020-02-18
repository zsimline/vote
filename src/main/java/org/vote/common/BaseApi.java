package org.vote.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.vote.common.Code;

/**
 * 基础API类
 * 所有API类继承自此类
 */
public class BaseApi extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // json处理器
  private final Gson gson = new Gson();

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response 响应对象
   * @param status   自定义的返回码
   * @throws ServletException
   * @throws IOException
   */
  protected void complete(HttpServletResponse response, int status) throws ServletException, IOException {
    Code code = new Code(status);
    String jsonStr = gson.toJson(code);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response 响应对象
   * @param status   自定义的返回码
   * @param extraStr 额外的说明
   * @throws ServletException
   * @throws IOException
   */
  protected void complete(HttpServletResponse response, int status, String extraStr) throws ServletException, IOException {
    Code code = new Code(status);
    code.setCodeDesc(extraStr);
    String jsonStr = gson.toJson(code);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 向用户发送JSON
   * 将对象转换为JSON字符串并发送
   * 
   * @param response 响应对象
   * @param obj 要转换的对象
   * @throws IOException
   */
  protected void sendJSON(HttpServletResponse response, Object obj) throws IOException {
    Gson gson = new Gson();
    String jsonStr = gson.toJson(obj);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }

  /**
   * 向用户发送JSON
   * 将对象转换为JSON字符串并发送
   * 
   * @param response 响应对象
   * @param objs 要转换的对象
   * @throws IOException
   */
  protected void sendJSON(HttpServletResponse response, List<?> objs) throws IOException {
    Gson gson = new Gson();
    String jsonStr = gson.toJson(objs);
    response.getWriter().write(jsonStr);
    response.setStatus(200);
  }
}