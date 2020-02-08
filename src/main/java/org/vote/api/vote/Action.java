package org.vote.api.vote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Entry;
import org.vote.beans.PostAction;
import org.vote.common.BaseApi;
import org.vote.common.Utils;

/**
 * 处理投票
 */
@WebServlet("/api/vote/action")
public class Action extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Action() {
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PostAction postAction = (PostAction) Utils.postDataToObj(request, PostAction.class);
    
    try {
      for (int id : postAction.getIds()) {
        Entry entry = (Entry) getInstanceById(Entry.class, id);
        entry.setAcquisition(entry.getAcquisition() + 1);
        updateInstance(entry);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  

}
