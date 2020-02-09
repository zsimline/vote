package org.vote.view.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.common.BaseView;

/**
 * 显示账户验证页面
 */
@WebServlet("/user/activation")
public class Activation extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String code = request.getParameter("code");    
    request.setAttribute("email", email);
    request.setAttribute("code", code);
    render(request, response, "/template/user/activation.jsp");
  }
}
