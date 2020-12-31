package com.servlets.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "Login Servlet",
		urlPatterns = {"/LoginServlet"},
		initParams = {
				@WebInitParam(name="user", value="Chaithra"),
				@WebInitParam(name="password", value="pwd")
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String HTML_START="<html><body>";
	private static final String HTML_END="</body></html>";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
 	public void init() throws ServletException {
	// TODO Auto-generated method stub
	if(getServletContext().getInitParameter("DBURL").
			equals("jdbc:mysql://localhost/mysql_db") && 
			getServletContext().getInitParameter("DBUser").
			equals("mysql_user") &&
			getServletContext().getInitParameter("DBpassword").
			equals("mysql_pwd"))
		getServletContext().setAttribute("DBSuccess", "True");
	else throw new ServletException("DBCOnnectionError");
}   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.setContentType("text/html; charset=utf-8");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		PrintWriter out= response.getWriter();
//		Date date=new Date();
//		out.println(HTML_START+"<h2>Hi You created your servlet go ahead with the next understanding</h2><br/><h3>Date:"+date+"</h3>"+HTML_END);
//	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user");
		String password=request.getParameter("password");
		String userid_param=getServletConfig().getInitParameter("user");
		String password_param=getServletConfig().getInitParameter("password");
		log("user: "+user+"password: "+password);
		if(user.equals(userid_param) && password.equals(password_param))
			response.sendRedirect("LoginSuccess.jsp");
		else {
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out=response.getWriter();
			out.println("<font color=red>Either username or opassword is wrong</font>");
			rd.include(request, response);
		}
	}

}
