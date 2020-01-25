package org.vote.processor.ticket;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import org.vote.beans.Activity;
import org.vote.common.Code;
import org.vote.common.HibernateUtil;
import org.vote.common.UUIDTool;

/**
 * 处理创建活动
 */
@WebServlet("/v2/create")
public class Create extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Create() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 只处理 multipart 类型的表单数据
    boolean multipart = ServletFileUpload.isMultipartContent(request);
    if (multipart) {
      Activity activity = new Activity();

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

        // TODO 设置发布者
        activity.setPublisher(1000001);

        while (iter.hasNext()) {
          FileItem item = iter.next();

          if (item.isFormField()) { // 普通表单数据
            // 表单名
            String fieldName = item.getFieldName();

            // 表单值
            String fieldContent = item.getString("UTF-8");
            
            if (fieldName.equals("title")) {
              activity.setTitle(fieldContent);
            } else if (fieldName.equals("suffix")) {
              activity.setSuffix(fieldContent);
            } else if (fieldName.equals("quantifier")) {
              activity.setQuantifier(fieldContent);
            } else if (fieldName.equals("description")) {
              activity.setDescription(fieldContent);
            } else if (fieldName.equals("voteTimeStart")) {
              activity.setVoteTimeStart(new Date(Long.parseLong(fieldContent)));
            } else if (fieldName.equals("voteTimeEnd")) {
              activity.setVoteTimeEnd(new Date(Long.parseLong(fieldContent)));
            } else if (fieldName.equals("signUpTimeStart")) {
              activity.setApplyTimeStart(new Date(Long.parseLong(fieldContent)));
            } else if (fieldName.equals("signUpTimeEnd")) {
              activity.setApplyTimeEnd(new Date(Long.parseLong(fieldContent)));
            } else if (fieldName.equals("maxium")) {
              activity.setMaxium(Integer.parseInt(fieldContent));
            } else if (fieldName.equals("options")) {
              activity.setOptions(fieldContent);
            } 
          } else { // 文件数据
            String filename = item.getName();
            String ext = filename.substring(filename.indexOf(".") + 1);

            // 文件后缀名不为 jpg/png
            if (!ext.equals("jpg") && !ext.equals("png")) {
              completed(response, 1002);
            }

            // 定义上传文件路径
            String path = request.getSession().getServletContext().getRealPath("/") + "uploads/";

            // 定义本机存储的文件名
            String localFileName = UUIDTool.getUUID() + "." + ext;
            activity.setImgName(localFileName);

            // 存储文件
            File file = new File(path, localFileName);
            item.write(file);
          }
        }
        
        // 设置UUID主键
        activity.setId(UUIDTool.getUUID());

        // 执行数据存储
        if (dbExcute(activity)) {
          completed(response, 1000);
        } else {
          completed(response, 1001);
        }
      } catch (Exception e) {
        e.printStackTrace();
        completed(response, 1001);
      }
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  /**
   * 向用户返回操作执行的结果
   * @param response Servlet响应对象
   * @param status 自定义的返回码
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
   * 执行新建活动的数据库操作
   * @param activity 活动实例
   * @return true/false 新建活动成功/失败
   */
  private boolean dbExcute(Activity activity) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();

      // 创建投票表
      SQLQuery query = session.createSQLQuery("call create_ticket_table(:uuid)");
      query.setParameter("uuid", activity.getId());
      query.executeUpdate();
      
      session.save(activity);
      
      transaction.commit();
      session.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }
}
