package org.vote.processor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class HelloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public HelloServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");    
    response.setContentType("text/html;charset=UTF-8");

    System.out.println(request.getContentType());

    /*Session session = HibernateUtil.getSessionFactory().openSession();    
    Transaction transaction = session.beginTransaction();
    
    transaction.begin();
    //session.save();
    transaction.commit();
    session.close();*/
    
    response.setStatus(200);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
