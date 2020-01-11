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

    Session session = HibernateUtil.getSessionFactory().openSession();    
    Transaction transaction = session.beginTransaction();
    
    transaction.begin();
    session.save();
    transaction.commit();
    session.close();
    
    response.setStatus(200);
  }
}
