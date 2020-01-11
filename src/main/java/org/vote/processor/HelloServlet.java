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

import org.vote.beans.SignUp;

@WebServlet("/main")
public class HelloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public HelloServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    
    response.setContentType("text/html;charset=UTF-8");

    SignUp signup = new SignUp();

    signup.setAid("4028aa496f93b54f016f93b918780002");
    signup.setTitle("张艳泽");
    signup.setDescription("真帅呀");
    signup.setImgAddr("https://img.baidu.com/1.jpg");
    
    signup.setSex(true);
    signup.setAge(23);
    signup.setWechat("zyz97532");
    signup.setName("张艳泽");
    signup.setEmail("zsimline@163.com");
    signup.setClassdesc("数学与信息技术学院网络工程本科1班");
    signup.setCompany("小米科技");
    signup.setAddress("河北省沧州市南皮县寨子镇方庄村");
    signup.setSchool("邢台学院");
    signup.setTelephone("19931926703");
     
    Session session = HibernateUtil.getSessionFactory().openSession();    
    Transaction transaction = session.beginTransaction();
    
    transaction.begin();
    session.save(signup);
    transaction.commit();
    session.close();
    
    response.setStatus(200);
  }
}
