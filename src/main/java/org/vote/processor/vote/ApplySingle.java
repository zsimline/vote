package org.vote.processor.vote;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import org.vote.beans.Apply;
import org.vote.common.Code;
import org.vote.common.HibernateUtil;
import org.vote.common.UUIDTool;
import org.vote.common.Utils;

/**
 * 处理创建活动
 */
@WebServlet("/v2/apply")
public class ApplySingle extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ApplySingle() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 只处理 multipart 类型的表单数据
    boolean multipart = ServletFileUpload.isMultipartContent(request);
    if (multipart) {
      Apply apply = new Apply();

      // 创建磁盘文件工厂
      DiskFileItemFactory factory = new DiskFileItemFactory();

      // 设置缓冲区大小
      factory.setSizeThreshold(1024 * 1024 * 2);

      // 创建文件上传处理器
      ServletFileUpload upload = new ServletFileUpload(factory);

      // 单个文件大小不超过1M
      upload.setFileSizeMax(1024 * 1024);

      try {
        List<FileItem> items = upload.parseRequest(request);
        Iterator<FileItem> iter = items.iterator();

        while (iter.hasNext()) {
          FileItem item = iter.next();

          if (item.isFormField()) { // 普通表单数据
            // 表单名
            String fieldName = item.getFieldName();

            // 表单值
            String fieldContent = item.getString("UTF-8");

            if (fieldName.equals("aid")) {
              apply.setAid(fieldContent);
            } else if (fieldName.equals("title")) {
              apply.setTitle(fieldContent);
            } else if (fieldName.equals("description")) {
              apply.setDescription(fieldContent);
            } else if (fieldName.equals("name")) {
              apply.setName(fieldContent);
            } else if (fieldName.equals("sex")) {
              apply.setSex(Byte.valueOf(fieldContent));
            } else if (fieldName.equals("age")) {
              apply.setAge(Integer.valueOf(fieldContent));
            } else if (fieldName.equals("telephone")) {
              apply.setTelephone(fieldContent);
            } else if (fieldName.equals("email")) {
              apply.setEmail(fieldContent);
            } else if (fieldName.equals("school")) {
              apply.setSchool(fieldContent);
            } else if (fieldName.equals("company")) {
              apply.setCompany(fieldContent);
            } else if (fieldName.equals("address")) {
              apply.setAddress(fieldContent);
            }
          } else { // 文件数据
            String filename = item.getName();
            String ext = filename.substring(filename.indexOf(".") + 1);

            // 文件后缀名不为 jpg/png
            if (!ext.equals("jpg") && !ext.equals("png")) {
              completed(response, 1402);
              return;
            }

            // 定义上传文件路径
            String basePath = request.getSession().getServletContext().getRealPath("/") + "uploads/";
            String fullPath = Utils.mkdirByDate(basePath);

            // 定义本机存储的文件名
            String localFileName = UUIDTool.getUUID() + "." + ext;
            apply.setImgName(localFileName);

            // 存储文件
            File file = new File(fullPath, localFileName);
            item.write(file);
          }
        }

        // 执行数据存储
        if (dbExcute(apply)) {
          completed(response, 1400);
        } else {
          completed(response, 1401);
        }
      } catch (Exception e) {
        e.printStackTrace();
        completed(response, 1401);
      }
    }
  }

  /**
   * 向用户返回操作执行的结果
   * 
   * @param response Servlet响应对象
   * @param status   自定义的返回码
   * @throws ServletException
   * @throws IOException
   */
  private void completed(HttpServletResponse response, int status) throws ServletException, IOException {
    Code code = new Code(status);
    Gson gson = new Gson();
    String jsonObj = gson.toJson(code);
    response.getWriter().write(jsonObj);
    response.setStatus(200);
  }

  /**
   * 执行新建报名的数据库操作
   * 
   * @param apply 报名实例
   * @return true/false 新建报名成功/失败
   */
  private boolean dbExcute(Apply apply) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
      session.save(apply);
      transaction.commit();
      session.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
