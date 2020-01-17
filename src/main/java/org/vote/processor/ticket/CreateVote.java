package org.vote.processor.ticket;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

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
import org.vote.common.HibernateUtil;

import org.vote.beans.Activity;
import org.vote.common.UUIDTool;

/**
 * 处理创建投票
 */
@WebServlet("/v2/create")
public class CreateVote extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public CreateVote() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 只处理 multipart 类型的表单数据
    boolean multipart = ServletFileUpload.isMultipartContent(request);
    if (multipart) {
      Activity activity = new Activity();

      // 创建磁盘文件工厂
      DiskFileItemFactory factory = new DiskFileItemFactory();

      // 创建文件上传处理器
      ServletFileUpload upload = new ServletFileUpload(factory);

      try {
        List<FileItem> items = upload.parseRequest(request);
        Iterator<FileItem> iter = items.iterator();
        activity.setPublisher(1000001);
        activity.setOptions("1,2,3");
        while (iter.hasNext()) {
          FileItem item = iter.next();

          if (item.isFormField()) { // 普通表单数据
            // 表单名
            String fieldName = item.getFieldName();

            // 表单值
            String fieldContent = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");

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
            } 
          } else { // 文件数据
            String filename = item.getName();
            String ext = filename.substring(filename.indexOf(".") + 1);

            // 文件后缀名不为 jpg/png
            if (!ext.equals("jpg") && !ext.equals("png")) {

            }

            // 定义上传文件路径
            String path = request.getSession().getServletContext().getRealPath("/") + "uploads/";

            // 定义本机存储的文件名
            String localFileName = UUIDTool.getUUID() + "." + ext;
            activity.setImgName(localFileName);

            // 设置缓冲区大小
            factory.setSizeThreshold(1024 * 1024 * 2);

            // 设置临时目录
            factory.setRepository(new File("uploadtemp"));

            // 单个文件大小不超过2M
            upload.setFileSizeMax(1024 * 1024 * 2);

            // 存储文件
            File file = new File(path, localFileName);
            item.write(file);
          }
        }
        // 执行数据存储
        if (dbExcute(activity)) {
          response.setStatus(200);
        } else {
          response.setStatus(502);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  private boolean dbExcute(Activity activity) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();
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