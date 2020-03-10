package org.vote.api.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;

/**
 * 处理账户登录
 */
@WebServlet("/api/admin/user_freeze")
public class UserFreeze extends BaseApi {
  private static final long serialVersionUID = 1L;

  public UserFreeze() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    long uid = Long.valueOf(request.getParameter("uid"));
    User user = (User) DBUtil.getInstanceById(User.class, uid);
    char status = request.getParameter("status").charAt(0);
    
    if (status == 'y' ) {
      user.setIsActive(true);
    } else {
      user.setIsActive(false);
    }

    if (DBUtil.updateInstance(user)) {
      complete(response, 5000);
    } else {
      complete(response, 5001);
    }
  }
}