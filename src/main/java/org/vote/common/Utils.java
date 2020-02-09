package org.vote.common;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
   * @param clazz   实例类
   * @return 实例对象
   */
  public static Object postDataToObj(HttpServletRequest request, Class<?> clazz) {
    String jsonStr = null;
    try {
      jsonStr = IOUtils.toString(request.getInputStream(), "UTF-8");
      return gson.fromJson(jsonStr, clazz);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
      System.out.println(jsonStr);
      return null;
    }
  }

  /**
   * HTTPS 请求
   * 
   * @param urlStr url字符串
   * @return 获取的数据
   */
  public static String httpsGet(String urlStr) {
    try {
      URL url = new URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/json");
      conn.connect();
      
      // 将输入流转换为字符串并返回
      return IOUtils.toString(conn.getInputStream(), "UTF-8");
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return null;
    } catch (ProtocolException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}