package org.vote.common;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

/**
 * 通用工具包
 */
public class Utils {

  // json 解析器
  private static Gson gson = new Gson();

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

  /**
   * 按时间创建多级目录
   * 
   * @param basePath 文件夹基目录
   * @return true/false 创建目录成功/失败
   */
  public static String mkdirByDate(String basePath) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String fullPath = basePath + simpleDateFormat.format(new Date());
    File directory = new File(fullPath);
    if (!directory.exists()) {
      return directory.mkdirs() ? fullPath : null;
    }
    return fullPath;
  }
  
  /**
   * 将JSON数据转换为对象
   *
   * @param request 请求对象
   * @param clazz 实例类
   * @return 实例对象
   */
  public static Object postDataToObj(HttpServletRequest request, Class<?> clazz) {
    String jsonStr;
    try {
      jsonStr = IOUtils.toString(request.getInputStream(), "UTF-8");
      return gson.fromJson(jsonStr, clazz);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}