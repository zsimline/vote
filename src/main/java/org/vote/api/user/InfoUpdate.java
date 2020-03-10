package org.vote.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.User;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;
import org.vote.common.MD5;
import org.vote.common.Utils;

/**
 * 处理修改用户信息
 */
@WebServlet("/api/user/info_update")
public class InfoUpdate extends BaseApi {
  private static final long serialVersionUID = 1L;

  public InfoUpdate() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User newUser = (User) Utils.postDataToObj(request, User.class);
    String code = (String) request.getSession().getAttribute("code");
    if (code != null && newUser != null) {
      User oldUser = (User) DBUtil.getInstanceById(User.class, newUser.getId());

      // 用户信息不匹配无法完成信息的更改
      if (oldUser == null || !oldUser.getPassword().equals(code)) {
        complete(response, 2202); return ;
      } else {
        oldUser.setOrganization(newUser.getOrganization());
        oldUser.setPassword(MD5.md5(newUser.getPassword()));
        
        // 更新用户实例
        if (DBUtil.updateInstance(oldUser)) {
          request.getSession().removeAttribute("uid");
          complete(response, 2200);
        } else {
          complete(response, 2201);
        }
      }
    } else {
      complete(response, 2201);
    }
    request.removeAttribute("code");
  }
}