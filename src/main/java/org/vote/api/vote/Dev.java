package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.vote.common.BaseApi;


/**
 * 获取报名数据
 */
@WebServlet("/api/vote/dev")
public class Dev extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Dev() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


  }




}
