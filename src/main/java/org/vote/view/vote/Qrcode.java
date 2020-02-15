package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
import org.vote.common.BaseView;

/**
 * 显示链接与二维码页面
 */
@WebServlet("/vote/qrcode")
public class Qrcode extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Activity activity = (Activity) getInstanceById(Activity.class, request.getParameter("aid"));
    if (activity != null) {
      request.setAttribute("activity", activity);
      render(request, response, "/template/vote/qrcode.jsp");
    } else {
      response.sendRedirect("/index/error");
    }
  }
}