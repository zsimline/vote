package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.api.user.Identify;
import org.vote.beans.Entry;
import org.vote.common.BaseApi;
import org.vote.common.DBUtil;

/**
 * 处理审核报名
 */
@WebServlet("/api/vote/entry/freeze")
public class EntryFreeze extends BaseApi {
  private static final long serialVersionUID = 1L;

  public EntryFreeze() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (!Identify.isMyActivity(request, response)) {
      complete(response, 1602); return ;
    }

    Entry entry = null;
    String id = request.getParameter("id");
    try {
      entry = (Entry) DBUtil.getInstanceById(Entry.class, Long.valueOf(id));
      entry.setIsFreeze(true);
    } catch (NumberFormatException e) {
      e.printStackTrace();
      complete(response, 1601); return ;
    }

    if (DBUtil.updateInstance(entry)) {
      sendJSON(response, entry);
    } else {
      complete(response, 1601);
    }
  }
}