package org.vote.processor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.vote.common.HibernateUtil;

import org.vote.beans.Ticket;

@WebServlet("/main")
public class HelloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public HelloServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    
    response.setContentType("text/html;charset=UTF-8");

    Ticket ticket = new Ticket();
    ticket.setOpenid("cascsa_casvas5584");
    ticket.setNickname("梦醒时夜续");
    ticket.setSex(true);
    ticket.setHeadimgurl("headimgurl");
    ticket.setTimestamp(new Date());
    ticket.setIpaddr("192.168.43.172");
     
    Session session = HibernateUtil.getSessionFactory().openSession();    
    Transaction transaction = session.beginTransaction();
    
    transaction.begin();
    session.save(ticket);
    transaction.commit();
    session.close();
    
    response.setStatus(200);
  }
}
