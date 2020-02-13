package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Entry;
import org.vote.common.BaseApi;

/**
 * 处理审核报名
 */
@WebServlet("/api/vote/entry/freeze")
public class EntryFreeze extends BaseApi {
  private static final long serialVersionUID = 1L;

  public EntryFreeze() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (!isMyActivity(request, response)) {
      complete(response, 1602);
      return ;
    }

    String id = request.getParameter("id");
    Entry entry = (Entry)getInstanceById(Entry.class, Long.valueOf(id));
    entry.setIsFreeze(true);
    if (updateInstance(entry)) {
      sendJSON(response, entry);
    } else {
      complete(response, 1601);
    }
  }
}