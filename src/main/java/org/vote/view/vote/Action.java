package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;
import org.vote.common.DBUtil;
import org.vote.common.OAuth;

/**
 * 显示投票页面
 */
@WebServlet("/vote/action")
public class Action extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    String aid = request.getParameter("aid");
    Activity activity = (Activity) DBUtil.getInstanceById(Activity.class, aid);

    if (activity == null) {
     render404(response);
    } else if (!hasAuth(request)) {
      response.sendRedirect(OAuth.getAuthCodeApiAddress(aid));
    } else {
      // 记录访问数量
      activity.setSumVisited(activity.getSumVisited() + 1);
      DBUtil.updateInstance(activity);
      request.setAttribute("activity", activity);
      render(request, response, "/template/vote/action.jsp");
    }
  }

  /**
   * 判断投票者是否认证
   * 
   * @param request 请求对象
   * @return 已认证返回true, 否则返回false
   * @throws ServletException
   * @throws IOException
   */
  private boolean hasAuth(HttpServletRequest request) {
    Object openid = request.getSession().getAttribute("openid");
    return openid != null ? true : false;
  }
}