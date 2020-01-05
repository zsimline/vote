import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.Database;;

@WebServlet("/main")
public class HelloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public HelloServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=UTF-8");

    Database db = new Database();
    String sql = "select * from test";
    ResultSet res = db.executeQuery(sql);

    try {
      while (res.next()) {
        System.out.println(res.getString("dd"));
        response.getWriter().write(res.getString("dd"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
        
    response.setStatus(200);
  }
}
