package org.vote.common;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * 通用工具包
 */
public class Utils {
  // json 解析器
  private static final Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

  /**
   * 获取JSON解析器
   * 
   * @return JSON解析器
   */
  public static Gson getGson() {
    return gson;
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

  /**
   * 获取用户真实IP
   * 
   * @param request 请求对象
   * @return IP地址
   */
  public static String getRealIp(HttpServletRequest request) {
    String[] ips = request.getHeader("x-forwarded-for").split(", ");
    return ips.length > 0 ?  ips[0]  : "127.0.0.1";
  }
}