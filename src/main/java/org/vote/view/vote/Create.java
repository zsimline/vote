package org.vote.view.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseView;

/**
 * 显示创建投票页面
 */
@WebServlet("/vote/create")
public class Create extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uid = userIdentify(request, response);
    if (uid == null) {
      response.sendRedirect("/user/login");
    } else {
      display(request, response, "/template/vote/create.jsp");
    }
  }
}
