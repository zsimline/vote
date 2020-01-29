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
@WebServlet("/vote/item_manage")
public class ItemManage extends BaseView {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("aid", request.getParameter("aid"));
    display(request, response, "/template/vote/item_manage.jsp");
  }
}