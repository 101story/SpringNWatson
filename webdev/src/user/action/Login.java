package user.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cookie.Cooker;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static Logger logger = Logger.getLogger(Login.class);
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cooker cooker = new Cooker(request);
		
		String user_id = null;		
		if(cooker.exist("user_id")){
			user_id = cooker.getValue("user_id");
		}
		request.setAttribute("user_id", user_id);
		doProcess(request, response, "/WEB-INF/user/login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
//		String user_pwd = DigestUtils.sha512Hex(request.getParameter("user_pwd"));
		String user_pwd = request.getParameter("user_pwd");
		
		HttpSession session = request.getSession();
		
		logger.info("user id : "+user_id);
		logger.info("user pwd : "+user_pwd);
		

		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select user_name");
		sql.append(" from t_mem");
		sql.append(" where user_id = ?");
		sql.append(" and user_pw = ?");
		
		String user_name = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bigdata", "bigdata");
			st = cn.prepareStatement(sql.toString());
		    st.setString(1, user_id);
		    st.setString(2, user_pwd);
			rs = st.executeQuery();
			if(rs.next()){
				user_name= rs.getString("user_name");
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if(rs != null) try{rs.close();}catch(Exception e){}
			if(st != null) try{st.close();}catch(Exception e){}
			if(cn != null) try{cn.close();}catch(Exception e){}
		}
		
		if(user_name!=null){
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_name", user_name);
			
			String save_id = request.getParameter("save_id");
			response.addCookie(
					Cooker.createCookie("user_id", user_id, save_id != null ? 60*60*24*30:0));
			
			logger.info("로그인 성공");
			request.setAttribute("msg", "로그인 성공");
			request.setAttribute("url", "Main");
			doProcess(request, response, "result.jsp");
		}else{
			logger.info("로그인 실패");
			request.setAttribute("msg", "로그인 실패");
			request.setAttribute("url", "Login");
			doProcess(request, response, "result.jsp");
		}
	}

}
