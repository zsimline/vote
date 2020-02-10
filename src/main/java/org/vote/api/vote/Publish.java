package org.vote.api.vote;

import java.io.IOException;

import  org.apache.commons.codec.binary.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseApi;
import org.vote.common.UUIDTool;
import org.vote.common.Utils;

/**
 * 处理创建活动
 */
@WebServlet("/api/vote/publish")
public class Publish extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Publish() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Activity activity = (Activity) Utils.postDataToObj(request, Activity.class);
    if (activity == null) {
      complete(response, 1001);
      return;
    }

    // 生成活动的ID
    activity.setId(UUIDTool.getUUID());

    // 设置发布者
    try {
      String uid = userIdentify(request, response);
      activity.setPublisher(Long.valueOf(uid));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      complete(response, 1002);
      return;
    }

    // 执行数据存储
    if (handleFileUpload(request, activity) && createTicketTable(activity.getId()) 
        && saveInstance(activity)) {
      complete(response, 1000);
    } else {
      complete(response, 1001);
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
    return dbExcute(hql, "uuid", aid);
  }

  /**
   * 处理文件上传
   * 
   * @param request  请求对象
   * @param activity 活动实例
   * @return 文件上传失败/成功
   */
  private boolean handleFileUpload(HttpServletRequest request, Activity activity) {
    // 定义文件上传路径
    String fullPath = Utils.getUploadPath(request);

    // 定义本机存储的文件名
    String localFileName = UUIDTool.getUUID() + activity.getImgMain();
    activity.setImgMain(fullPath.substring(fullPath.indexOf("/uploads")) + "/" + localFileName);

    // 解码Base64字符串
    byte[] fileBytes = Base64.decodeBase64(activity.getImgData());
    return Utils.storeFile(fullPath + localFileName, fileBytes);
  }
}