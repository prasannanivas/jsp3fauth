package com.demoapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class welcomeServlet
 */
@WebServlet("/welcomeServlet")
public class welcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public welcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);  
		String duoStatus = (String) request.getParameter("state");
		if(duoStatus!=null)
		{
			session.setAttribute("duoverified", "true");
		}
		if(session==null)
		{
				//System.out.println("returning to home because"+session.getAttribute("otpverified") + session.getAttribute("duoverified"));
				response.sendRedirect("./logout");	
				return;
		}
		if(session.getAttribute("otpverified")==null || session.getAttribute("duoverified")==null)
		{
				System.out.println("returning to home because"+session.getAttribute("otpverified") + session.getAttribute("duoverified"));
				response.sendRedirect("./logout");	
				return;
		}
        session.setAttribute("isLoggedIn","true");
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			session.setAttribute("jaas","failure");
		}
		
        if(session.getAttribute("newUser") !=null && session.getAttribute("newUser").equals("true"))
        {
        	try {
		
        		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","Prasanna@27");
		
        		PreparedStatement pst = con.prepareStatement("insert into testtable (name , address,secretkey) values(?,?,?)");
        		pst.setString(1, (String)session.getAttribute("uname"));
        		pst.setString(2, (String)session.getAttribute("uemail"));
        		pst.setString(3, (String)session.getAttribute("secretKey"));
        		
        		
        		pst.executeUpdate();
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        		session.setAttribute("jaas","failure");
        	}
        }
        
        try {
		//response.sendRedirect("./summa");
        session.setAttribute("jaas","success");
		request.getRequestDispatcher("./welcome.jsp").forward(request,response);
		
        }
        catch(Exception e)
        {
			System.out.println("welcome called exception include");
			session.setAttribute("jaas","failure");
			request.getRequestDispatcher("./logout").forward(request,response);
			
		}
		
		System.out.println("From welcome page , after sending redirect with session");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
