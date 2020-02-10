package org.vote.common;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * 通用工具包
 */
public class Utils {
  // json 解析器
  private static Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

  /**
   * 获取JSON解析器
   * 
   * @return JSON解析器
   */
  public static Gson getGson() {
    return gson;
  }

  /**
   * 按时间创建多级目录
   * 
   * @param basePath 文件夹基目录
   * @return 创建成功后的全路径
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

  /**
   * 获取当天的文件上传路径
   * 
   * @param request 请求对象
   * @return 文件上传全路径
   */
  public static String getUploadPath(HttpServletRequest request) {
    String basePath = request.getSession().getServletContext().getRealPath("/") + "uploads/";
    return Utils.mkdirByDate(basePath);
  }
  
  /**
   * 存储文件
   * 
   * @param filePath 文件路径
   * @param data 文件数据
   * @return 存储文件成功成功/失败
   */
  public static boolean storeFile(String filePath, byte[] data) {
     FileOutputStream fileOutputStream;
     try {
       fileOutputStream = new FileOutputStream(filePath);
       fileOutputStream.write(data);
       fileOutputStream.close();
     } catch (FileNotFoundException e) {
       e.printStackTrace();
       return false;
     } catch (IOException e) {
       e.printStackTrace();
       return false;
     }

     return true;
  }
}