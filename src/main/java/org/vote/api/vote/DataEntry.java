package org.vote.api.vote;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Entry;
import org.vote.common.BaseApi;

/**
 * 获取报名数据
 */
@WebServlet("/api/vote/data_entry")
public class DataEntry extends BaseApi {
  private static final long serialVersionUID = 1L;

  public DataEntry() {
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
    int page = Integer.valueOf(request.getParameter("page"));
  
    String[] keys = { "aid" };
    Object[] values = { aid };

    return paginationQuery(Entry.class, keys, values, page, 15);
  }
}