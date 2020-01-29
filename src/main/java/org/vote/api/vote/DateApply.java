package org.vote.api.vote;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseApi;
import org.vote.beans.Activity;
import org.vote.beans.Apply;

/**
 * 获取报名数据
 */
@WebServlet("/api/vote/date_apply")
public class DateApply extends BaseApi {
  private static final long serialVersionUID = 1L;

  public DateApply() {
  }
  
  @SuppressWarnings("unused")
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String aid = request.getParameter("aid");
    Activity activity = (Activity)getInstanceById(Activity.class, aid);
    String uid = userIdentify(request, response);

    List<Apply> applys = fetchApplys(aid);


  }

  @SuppressWarnings("unchecked")
  private List<Apply> fetchApplys(String aid) {
    String hql = "FROM apply WHERE aid = :aid";
    String[] keys = {"aid"}, values = {aid};
    return (List<Apply>) getInstanceByHql(hql, keys, values);
  }
}
