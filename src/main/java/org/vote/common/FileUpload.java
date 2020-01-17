package org.vote.common;

import java.util.List;
import java.io.File;
import java.util.Iterator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUpload {
  public FileUpload() {
    // TODO
  }

  public void handleFileUpload(HttpServletRequest request, HttpServletResponse response) {
    boolean multipart = ServletFileUpload.isMultipartContent(request);
    if (multipart) {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);
      
      try {
        List<FileItem> items = upload.parseRequest(request);
        Iterator<FileItem> iter = items.iterator();
        
        while (iter.hasNext()) {
          FileItem item = iter.next();
          if (item.isFormField()) {  // 普通表单数据
            String fieldName = item.getFieldName();
            // new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
          } else { // 文件上传
            String filename = item.getName();
            String ext = filename.substring(filename.indexOf(".") + 1);
            long millis = System.currentTimeMillis(); // 时间戳

            // 定义上传文件路径
            String path = request.getSession().getServletContext().getRealPath("/") + "media/";
            File file = new File(path, millis + "." + ext);

            // 临时文件
            factory.setSizeThreshold(1024 * 1024 * 2);
            factory.setRepository(new File("uploadtemp"));

            // 单个文件大小不超过5M
            upload.setFileSizeMax(1024 * 1024 * 5);

            // item.write(file);
          }
        }
      } catch (FileUploadException e) {
        e.printStackTrace();
      }
    }
  }
}