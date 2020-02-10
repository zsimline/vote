package org.vote.api.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseApi;

/**
 * 处理账户激活
 */
@WebServlet("/api/user/activation")
public class Activation extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Activation() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String emailAddress = request.getParameter("email");
    String password = request.getParameter("code");

    User user = getUserByEmail(emailAddress);

    // 用户不存在
    if (user == null) {
      complete(response, 1302);
      return ;
    }
    
    // 密码错误
    if (!user.getPassword().equals(password)) {
      complete(response, 1303);
      return ;
    }

    // 设置账户为激活的
    // 更新用户实例
    user.setIsActive(true);
    if (updateInstance(user)) {
      complete(response, 1300);
    } else {
      complete(response, 1301);
    }
  }

  /**
   * 根据邮件地址获取用户
   * 
   * @param emailAddress 邮件地址
   * @return 用户实例
   */
  private User getUserByEmail(String emailAddress) {    
    List<?> results = conditionQuery(User.class, "email", emailAddress);
    return results.isEmpty() ? null : (User) results.get(0);
  }
}