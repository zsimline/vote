package org.vote.api.vote;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vote.beans.Activity;
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
    Activity activity = (Activity) getInstanceById(Activity.class, postAction.getAid());

    if (activity != null && checkTime(activity)) {
      int maximum = activity.getMaximum();
      List<Integer> ids = postAction.getIds();
      int idsLength = ids.size();

      for(int i = 0; i < idsLength && i < maximum; i++) {
        Entry entry = (Entry) getInstanceById(Entry.class, ids.get(i));
        if (entry != null && entry.getAid().equals(activity.getId())) {
          entry.setAcquisition(entry.getAcquisition() + 1);
          updateInstance(entry);
        }
      }
    }
  }

  //
  private boolean checkTime(Activity activity) {
    Date voteTimeStart  = activity.getVoteTimeStart();
    Date voteTimeEnd = activity.getVoteTimeEnd();
    Date  currentTime =  new Date();
    return voteTimeStart.before(currentTime) && voteTimeEnd.after(currentTime);
  }
}