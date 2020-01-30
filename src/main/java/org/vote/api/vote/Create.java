package org.vote.api.vote;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.vote.beans.Activity;
import org.vote.common.BaseApi;
import org.vote.common.UUIDTool;
import org.vote.common.Utils;

/**
 * 处理创建活动
 */
@WebServlet("/api/vote/create")
public class Create extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Create() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uid = userIdentify(request, response);
    if (uid == null) {
      completed(response, 1003);
      return ;
    }

    // 只处理 multipart 类型的表单数据
    boolean multipart = ServletFileUpload.isMultipartContent(request);
    if (multipart) {
      Activity activity = new Activity();

      // 设置发布者
      activity.setPublisher(Long.valueOf(uid));

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

            if (fieldName.equals("title")) {
              activity.setTitle(fieldContent);
            } else if (fieldName.equals("suffix")) {
              activity.setSuffix(fieldContent);
            } else if (fieldName.equals("quantifier")) {
              activity.setQuantifier(fieldContent);
            } else if (fieldName.equals("summary")) {
              activity.setSummary(fieldContent);
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
              return ;
            }

            // 定义上传文件路径
            String basePath = request.getSession().getServletContext().getRealPath("/") + "uploads/";
            String fullPath = Utils.mkdirByDate(basePath);

            // 定义本机存储的文件名
            String localFileName =  UUIDTool.getUUID() + "." + ext;
            activity.setImgMain(fullPath.substring(fullPath.indexOf("/uploads")) + "/" + localFileName);

            // 存储文件
            File file = new File(fullPath, localFileName);
            item.write(file);
          }
        }

        // 设置UUID主键
        activity.setId(UUIDTool.getUUID());

        // 执行数据存储
        if (createTicketTable(activity.getId()) && saveInstance(activity)) {
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

  /**
   * 创建投票表
   * 
   * @param aid 活动ID
   * @return 创建投票表成功/失败
   */
  private boolean createTicketTable(String aid) {
    // 调用创建投票表事务
    String hql = "call create_ticket_table(:uuid)";
    String[] keys = {"uuid"}, values = {aid};
    return dbExcute(hql, keys, values);
  }
}
