package org.vote.api.vote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Apply;
import org.vote.beans.Entry;
import org.vote.common.BaseApi;

/**
 * 处理更新报名信息
 */
@WebServlet("/api/vote/review")
public class Review extends BaseApi {
  private static final long serialVersionUID = 1L;

  public Review() {
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (!isMyActivity(request, response)) {
      complete(response, 1602);
      return ;
    }
    
    char status = request.getParameter("status").charAt(0);
    int id = Integer.valueOf(request.getParameter("id"));
    Apply apply = (Apply)getInstanceById(Apply.class, id);

    if (status == 'y') {
      apply.setStatus('y');
    } else if (status == 'n') {
      apply.setStatus('n');
    }
    
    Entry entry = new Entry();
    entry.setAid(request.getParameter("aid"));
    entry.setTitle(apply.getTitle());
    entry.setImgEntry(apply.getImgEntry());
    entry.setIntroduction(apply.getIntroduction());
    entry.setNumber(1);

    if (updateInstance(apply) && saveInstance(entry)) {
      complete(response, 1600);
    } else {
      complete(response, 1601);
    }
  }
}
