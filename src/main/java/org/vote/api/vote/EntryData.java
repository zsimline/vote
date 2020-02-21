package org.vote.api.vote;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Entry;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;

/**
 * 获取报名数据
 */
@WebServlet("/api/vote/entry/data")
public class EntryData extends BaseApi {
  private static final long serialVersionUID = 1L;

  public EntryData() {
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    sendJSON(response, fetchEntrys(request));
  }

  /**
   * 获取报名数据
   * 
   * @param request 请求对象
   * @return 报名数据集合
   */
  private List<?> fetchEntrys(HttpServletRequest request) {
    String aid = request.getParameter("aid");
    int page = 1;
    try {
      page = Integer.valueOf(request.getParameter("page"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    String[] keys = {"aid"};
    Object[] values = { aid };
    return DBUtil.paginationQuery(Entry.class, keys, values, page, 20);
  }
}