package org.vote.common;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
