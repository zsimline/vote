package org.vote.common;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用工具包
 */
public class Utils {

  /**
   * 将输入流转换为JSON字符串
   * 
   * @param request Servlet 请求对象
   * @return JSON字符串
   */
  public static String inputToJsonStr(HttpServletRequest request) {
    try {
      return IOUtils.toString(request.getInputStream(), "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
