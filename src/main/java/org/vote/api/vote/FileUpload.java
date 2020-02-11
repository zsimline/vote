package org.vote.api.vote;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.InvalidFileNameException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseApi;
import org.vote.common.UUIDTool;

/**
 * 通用文件上传处理
 */
@WebServlet("/api/vote/file_upload")
public class FileUpload extends BaseApi {
  private static final long serialVersionUID = 1L;

  // 文件上传基目录
  private static final String basePath = "/opt/uploads/";

  // 磁盘文件工厂
  private DiskFileItemFactory factory;

  // 文件上传处理器
  private ServletFileUpload upload;

  public FileUpload() {
    // 创建磁盘文件工厂
    factory = new DiskFileItemFactory();

    // 设置缓冲区大小为2M
    factory.setSizeThreshold(1024 * 1024 * 2);

    // 创建文件上传处理器
    upload = new ServletFileUpload(factory);
    
    // 单个文件大小不超过1M
    upload.setFileSizeMax(1024 * 1024);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      FileItem item = upload.parseRequest(request).get(0);
      
      // 获取文件名与文件后缀名
      String fileName = item.getName();
      String suffix = fileName.endsWith(".jpg") ? ".jpg"  : ".png";

      // 定义文件的本地存储路径与URL地址
      String localFileName = UUIDTool.getUUID() + suffix;
      String uploadPath = getUploadPath(request);
      String fileUrl = uploadPath.substring(uploadPath.indexOf("/uploads")) + "/" + localFileName;
      
      // 存储文件
      File file = new File(uploadPath, localFileName);
      item.write(file);

      complete(response, 3000, fileUrl);
    } catch (FileUploadException e) {
      e.printStackTrace();
      complete(response, 3002);
    }  catch (InvalidFileNameException e) {
      e.printStackTrace();
      complete(response, 3004);
    } catch (Exception e) {
      e.printStackTrace();
      complete(response, 3001);
    }
  }

  /**
   * 获取当天的文件上传路径
   * 
   * @param request 请求对象
   * @return 文件上传全路径
   */
  private String getUploadPath(HttpServletRequest request) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String fullPath = basePath + simpleDateFormat.format(new Date());
    File directory = new File(fullPath);
    if (!directory.exists()) {
      return directory.mkdirs() ? fullPath : null;
    }
    return fullPath;
  }
}