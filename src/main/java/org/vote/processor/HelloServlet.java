package org.vote.processor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.vote.common.HibernateUtil;

import org.vote.beans.User;

@WebServlet("/main")
public class HelloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public HelloServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    
    response.setContentType("text/html;charset=UTF-8");

    User user = new User();
    user.setEmail("zsimline@127.com");
    user.setOrganization("邢台学院");
    user.setPassword("76c6560484e449f590010d6fc9ce3055");
    
    Session session = HibernateUtil.getSessionFactory().openSession();    
    Transaction transaction = session.beginTransaction();
    
    transaction.begin();
    session.save(user);
    transaction.commit();
    session.close();
    
    response.setStatus(200);
  }
}
