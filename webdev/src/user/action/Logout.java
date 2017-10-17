package user.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private void doProcess(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		request.setAttribute("msg", "로그아웃");
		request.setAttribute("url", "Login");
		doProcess(request, response, "result.jsp");
//		RequestDispatcher dispatchar = request.getRequestDispatcher("result.jsp");
//		dispatchar.forward(request, response);
	}
}
