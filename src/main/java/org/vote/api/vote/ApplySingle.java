package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Apply;
import org.vote.common.BaseApi;
import org.vote.common.Utils;

/**
 * 处理活动报名
 */
@WebServlet("/api/vote/apply")
public class ApplySingle extends BaseApi {
  private static final long serialVersionUID = 1L;

  public ApplySingle() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    Apply apply = (Apply) Utils.postDataToObj(request, Apply.class);

    if (aid == null || apply == null) {
      complete(response, 1401); return;
    } else {
      apply.setAid(aid);
    }

    // 设置审核状态
    apply.setStatus(isMyActivity(request, response) ? 'y' : 'w');

    // 执行数据存储
    if (saveInstance(apply)) {
      sendJSON(response, apply);
    } else {
      complete(response, 1401);
    }
  }
}