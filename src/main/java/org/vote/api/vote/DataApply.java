package org.vote.api.vote;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.Identify;
import org.vote.beans.Apply;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;

/**
 * 获取报名数据
 */
@WebServlet("/api/vote/data/apply")
public class DataApply extends BaseApi {
  private static final long serialVersionUID = 1L;

  public DataApply() {
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (Identify.isMyActivity(request, response)) {
      sendJSON(response, fetchApplys(request));
    } else {  // 报名数据不可被获取时返回空数据
      sendJSON(response, Collections.emptyList());     
    }
  }

  /**
   * 获取报名数据
   * 
   * @param request 请求对象
   * @return 报名数据集合
   */
  private List<?> fetchApplys(HttpServletRequest request) {
    String aid = request.getParameter("aid");

    char status = 'w';
    int page = 1;
    try {
      status = request.getParameter("status").charAt(0);
      page = Integer.valueOf(request.getParameter("page"));
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  
    String[] keys = { "aid", "status" };
    Object[] values = { aid, status };
    
    return DBUtil.paginationQuery(Apply.class, keys, values, page, 15);
  }
}