package org.vote.api.gather;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Interceptor;

import org.vote.common.TableNameMappingInterceptor;

/**
 * 获取与性别相关的统计数据
 */
@WebServlet("/api/gather/by_province")
public class ByProvince extends BaseGather {
  private static final long serialVersionUID = 1L;

  private String sql;

  public ByProvince() {
    this.sql = "select w.province, count(distinct w.openid) from `ticket` t left join `wechat` w on t.openid = w.openid group by w.province";
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Interceptor interceptor = new TableNameMappingInterceptor(request.getParameter("aid"));
    sendJSON(response, doGather(sql, interceptor));
  }
}