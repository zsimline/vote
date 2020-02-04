package org.vote.api.vote;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseApi;

/**
 * 获取报名数据
 */
@WebServlet("/api/vote/data_apply")
public class DataApply extends BaseApi {
  private static final long serialVersionUID = 1L;

  public DataApply() {
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");

    if (isMyActivity(aid, request, response)) {
      sendJson(response, fetchApplys(aid));
    } else {  // 报名数据不可被获取时返回空数据
      sendJson(response, Collections.emptyList());     
    }
  }


  /**
   * 获取报名数据
   * 
   * @param aid 活动ID
   * @return 报名数据
   */
  private List<?> fetchApplys(String aid) {
    String hql = "FROM Apply WHERE aid = :aid";
    String[] keys = {"aid"}, values = {aid};
    return getInstanceByHql(hql, keys, values);
  }
}