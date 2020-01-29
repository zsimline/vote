package org.vote.api.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.vote.beans.User;
import org.vote.common.BaseApi;
import org.vote.common.MD5;
import org.vote.common.UUIDTool;
import org.vote.common.CookieFactory;
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
    String jsonStr = Utils.inputToJsonStr(request);

    if (jsonStr != null) {
      Gson gson = new Gson();
      User userVerify = gson.fromJson(jsonStr, User.class);
      User user = getUserByEmail(userVerify.getEmail());

      // 验证账户是够存在
      if (user == null) {
        completed(response, 1202);
        return;
      }

      // 验证账户是否是已激活的
      if (user.getIsActive() == false) {
        completed(response, 1204);
        return;
      }

      try {
        if (MD5.verify(userVerify.getPassword(), user.getPassword())) {
          // 生成登录令牌
          String token = UUIDTool.getUUID();
          user.setToken(token);

          if (updateInstance(user)) {
            CookieFactory cookieFactory = new CookieFactory(request, response);
            cookieFactory.setCookie("uid", String.valueOf(user.getId()));
            cookieFactory.setCookie("token", token);
            completed(response, 1200);
          } else {
            completed(response, 1205);
          }
        } else {
          completed(response, 1203);
        }
      } catch (Exception e) {
        e.printStackTrace();
        completed(response, 1201);
      }
    }
  }

  /**
   * 根据邮件地址获取用户
   * 
   * @param emailAddress 邮件地址
   * @return 用户实例
   */
  @SuppressWarnings("unchecked")
  private User getUserByEmail(String emailAddress) {
    String hql = "FROM User WHERE email = :emailAddress";
    String[] keys = {"emailAddress"},values = {emailAddress};
    List<User> results = (List<User>)getInstanceByHql(hql, keys, values);
    return results.isEmpty() ? null : results.get(0);
  }
}
