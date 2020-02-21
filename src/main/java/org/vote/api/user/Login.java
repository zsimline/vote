package org.vote.api.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseApi;
import org.vote.common.MD5;
import org.vote.common.DBUtil;
import org.vote.common.Utils;

/**
 * 处理账户登录
 */
@WebServlet("/api/user/login")
public class Login extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Login() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User userVerify = (User) Utils.postDataToObj(request, User.class);
    User user = null;
    if (userVerify != null) {
      user = getUserByEmail(userVerify.getEmail());
    } else {
      complete(response, 1201); return ;
    }

    if (user == null) { // 账户不存在
      complete(response, 1202); return;
    } else if (!user.getIsActive()) { // 账户未激活
      complete(response, 1204); return;
    } else if (!MD5.verify(userVerify.getPassword(), user.getPassword())) { // 密码错误
      complete(response, 1203); return ;
    }

    // 保存登录状态
    request.getSession().setAttribute("uid", user.getId());
    complete(response, 1200);
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