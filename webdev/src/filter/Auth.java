package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Auth implements Filter{

	@Override
	public void destroy() {
		System.out.println("destroy()");
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("doFilter()");
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
				
		String contextPath = request.getContextPath();
		String action = request.getRequestURI().substring(contextPath.length());
		
		System.out.println(contextPath);
		System.out.println(request.getRequestURI());
		System.out.println(action);
		System.out.println(session.getAttribute("user_id"));
		
		
		if(!(action.equals("/session2/session_form.jsp")||
				action.equals("/session2/session_add.jsp")||
				action.equals("/session2/result.jsp"))){
			
			if(session.getAttribute("user_id")==""||session.getAttribute("user_id")==null){
				request.setAttribute("msg",	"먼저 로그인을 하셔야 합니다.");
				request.setAttribute("url",	contextPath+"/session2/session_form.jsp");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/session2/result.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init()");
		
	}

}
