package com.security;

import java.io.IOException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Driver extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		try {
			String configFileLocation = getServletContext().getRealPath("/secur.config");
		    System.err.println("fileerr" + configFileLocation);
		    System.setProperty("java.security.auth.login.config", configFileLocation);
			//System.setProperty("java.security.auth.login.config", "secur.config");
		}
		catch(Exception e)
		{
			if(session.getAttribute("jaas").equals("success")) {
				res.sendRedirect("/welcome.jsp");
				return;
			}
			res.sendRedirect("./auth.jsp");
			return;
		}
		
		boolean authflag = true; 
		
		LoginContext loginContext;
		try {
			loginContext = new LoginContext("jaasconfig", new callbackhandler(session));
			loginContext.login();
			
		} catch (LoginException e) {
			authflag = false;
		}
			
		
		if(authflag)
			res.sendRedirect("./welcome.jsp");
		else
			res.sendRedirect("/logout");
		
		

	}

}
