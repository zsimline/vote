package org.vote.api.vote;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import org.vote.beans.Activity;
import org.vote.beans.Entry;
import org.vote.beans.PostAction;
import org.vote.beans.Ticket;
import org.vote.beans.Wechat;
import org.vote.common.BaseApi;
import org.vote.common.CookieFactory;
import org.vote.common.DBUtil;
import org.vote.common.HibernateUtil;
import org.vote.common.TableNameMappingInterceptor;
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
    // 校验是否有权限投票
    String openid = getOpenid(request, response);
    if (openid == null) {
      complete(response, 2005); return;
    }
    
    // 获取用户上传的投票数据
    PostAction postAction = (PostAction) Utils.postDataToObj(request, PostAction.class);
    Activity activity = null;
    if (postAction == null) {
      complete(response, 2002); return;
    } else {
      activity = (Activity) DBUtil.getInstanceById(Activity.class, postAction.getAid());
      if (activity == null) {
        complete(response, 2003); return ;
      }
    }

    List<Integer> ids = postAction.getIds();
    String aid  = activity.getId();
    long sumVoted = activity.getSumVoted();
    Interceptor interceptor = new TableNameMappingInterceptor(aid);

    // 执行必要的校验
    if (!checkTime(activity)) {
      complete(response, 2004); return;
    } else if (hasVoted(openid, interceptor)) {
      complete(response, 2006); return;
    } else if (activity.getMaximum() < ids.size()) {
      complete(response, 2007); return;
    }
  
    for(long id : ids)  {
      Entry entry = (Entry) DBUtil.getInstanceById(Entry.class, id);
      if (entry == null || !entry.getAid().equals(aid) || entry.getIsFreeze()) {
        complete(response, 2008); return ;
      } else {
        entry.setAcquisition(entry.getAcquisition() + 1);
        activity.setSumVoted(++sumVoted);
      }

      // 创建投票实例
      Ticket ticket = new Ticket();
      ticket.setOpenid(openid);
      ticket.setWhom(id);
      ticket.setTimestamp(new java.sql.Date(new Date().getTime()));
      ticket.setIp(Utils.getRealIp(request));
      ticket.setReason(postAction.getReason());

      if (!DBUtil.updateInstance(entry)  || !saveTicket(ticket, interceptor)) {
        complete(response, 2001); return ;
      }
    }
    DBUtil.updateInstance(activity);
    complete(response, 2000);
  }

  /**
   * 保存数据实例
   * 
   * @param instance 数据实例
   * @param interceptor 拦截器
   * @return 操作执行成功/失败
   */
  protected boolean saveTicket(Object instance, Interceptor interceptor) {
    Session session = null;
    Transaction transaction = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession(interceptor);
      transaction = session.beginTransaction();
      session.save(instance);
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      return false;
    } finally {
      if (session != null) {
        session.close();
      }
    }

    return true;
  }

  /**
   * 检测投票时间
   * 判断是否在投票时间区间内
   * 
   * @param activity 活动实例
   * @return 在时间区间内返回true, 否则返回false
   */
  private boolean checkTime(Activity activity) {
    Date voteTimeStart  = activity.getVoteTimeStart();
    Date voteTimeEnd = activity.getVoteTimeEnd();
    Date  currentTime =  new Date();
    return voteTimeStart.before(currentTime) && voteTimeEnd.after(currentTime);
  }

  /**
   * 验证用户是否已经投过票
   * 
   * @param openid 微信用户id
   * @return 投过票则返回true, 否则返回false
   */
  protected boolean hasVoted(String openid, Interceptor interceptor) {
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession(interceptor);

      // 创建条件容器并附加条件
      Criteria criteria = session.createCriteria(Ticket.class); 
      criteria.add(Restrictions.eq("openid", openid));
      criteria.add(Restrictions.eq("timestamp",  new java.sql.Date(new Date().getTime())));

      return criteria.list().size() > 0 ? true : false;
    } catch (HibernateException e) {
      e.printStackTrace();
      return true;
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * 获取投票者openid
   * 
   * @param request  请求对象
   * @param response 响应对象
   * @return 投票者openid
   * @throws ServletException
   * @throws IOException
   */
  private String getOpenid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HashMap<String, String> cookieMap = CookieFactory.cookiesToHashMap(request);
    
    // 获取微信用户 openid 与访问令牌
    String openid = cookieMap.get("openid");
    String token = cookieMap.get("token");

    // 未携带认证信息
    if (openid == null || token == null) {
      return null;
    }

    // 已携带认证信息但认证失败
    Wechat wechat = (Wechat) DBUtil.getInstanceById(Wechat.class, openid);
    if (wechat == null || !wechat.getToken().equals(token)) {
      return null;
    }

    return openid;
  }
}