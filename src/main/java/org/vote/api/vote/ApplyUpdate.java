package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Apply;
import org.vote.common.BaseApi;
import org.vote.common.Utils;

import java.lang.NumberFormatException;

/**
 * 处理更新报名信息
 */
@WebServlet("/api/vote/apply_update")
public class ApplyUpdate extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ApplyUpdate() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (!isMyActivity(request, response)) {
      complete(response, 1503); return ;
    }
    
    // 从数据库中获取的报名数据
    Apply applyOld = getApplyInfo(request, response);

    // 用户更新的报名数据
    Apply applyNew = (Apply) Utils.postDataToObj(request, Apply.class);

    if (applyOld == null) {
      complete(response, 1502); return ;
    } else if ( applyNew == null) {
      complete(response, 1504); return ;
    } else {
      applyNew.setAid(applyOld.getAid());
      applyNew.setId(applyOld.getId());
      applyNew.setStatus(applyOld.getStatus());
    }

    // 处理图片更新
    if (applyNew.getImgEntry() == null) {
      applyNew.setImgEntry(applyOld.getImgEntry());
    }

    // 执行数据存储
    if (updateInstance((applyNew))) {
      sendJSON(response, applyNew);
    } else {
      complete(response, 1501);
    }
  }

  /**
   * 获取存储在数据库中的报名数据
   * 
   * @param request 请求对象
   * @param response 响应对象
   * @return 报名数据实例
   * @throws ServletException
   * @throws IOException
   */
  private Apply getApplyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Apply apply = null;
    try {
      String id = request.getParameter("id");
      apply = (Apply) getInstanceById(Apply.class, Long.valueOf(id));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return apply;
  }
}