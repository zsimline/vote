package org.vote.view.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseView;
import org.vote.common.DBUtil;

/**
 * 显示用户信息页面
 */
@WebServlet("/user/profile")
public class Profile extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User user = checkUserIdentify(request);
    if (user != null) {
      request.setAttribute("user", user);
      request.getSession().setAttribute("code", user.getPassword());
      render(request, response, "/template/user/profile.jsp");
    } else {
      render404(response);
    }
  }

  private User checkUserIdentify(HttpServletRequest request) {
    if (request.getAttribute("uid") != null) {
      return (User) DBUtil.getInstanceById(User.class, (long)request.getAttribute("uid"));
    } else {
      User user = getUserByEmail(request.getParameter("email"));
      return user != null && user.getPassword().equals(request.getParameter("code")) ? user : null;
    }
  }

    /**
   * 根据邮件地址获取用户
   * 
   * @param emailAddress 邮件地址
   * @return 用户实例
   */
  private User getUserByEmail(String emailAddress) {
    List<?> results = DBUtil.conditionQuery(User.class, "email", emailAddress);
    return results.isEmpty() ? null : (User) results.get(0);
  }
}