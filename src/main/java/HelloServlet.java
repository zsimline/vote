import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class HelloServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  public HelloServlet() {
    super();
  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=UTF-8");
    response.getWriter().write("Hello, Servlet!");
    response.setStatus(200);
  }
}
