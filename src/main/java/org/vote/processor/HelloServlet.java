package org.vote.processor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.vote.beans.Stock;
import org.vote.common.HibernateUtil;

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

    session.beginTransaction();
    Stock stock = new Stock();

    stock.setStockCode("4719");
    stock.setStockName("ZE");

    session.save(stock);
    session.getTransaction().commit();
        
    response.setStatus(200);
  }
}
