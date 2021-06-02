package com.demoapp;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.duosecurity.Client;
import com.duosecurity.exception.DuoException;
import com.duosecurity.model.Token;

/**
 * Servlet implementation class DUOauth
 */
@WebServlet("/DUOauth")
public class DUOauth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws DuoException 
     * @see HttpServlet#HttpServlet()
     */
	//
    public DUOauth() throws DuoException {
        super();
        
;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//System.out.println("I am called");
		 //@Value("${duo.redirect.uri}")
		HttpSession session = request.getSession(false);
		String uri;
		Client duoClient;
		try {
			duoClient = new Client.Builder("DIK5R4GZIBNOEKU41HKE", "pOvO7zzdtQMditoXDMWoct8N0qx65mX8qCQRobCH","api-6c0ca313.duosecurity.com","http://localhost:8080/demoapp/welcome").build();
			 // Step 4: Create the authUrl and redirect to it
			String state = duoClient.generateState();
			
			//Duo.Web.SignRequest
		    String authUrl = duoClient.createAuthUrl(request.getParameter("name"), state);
		    System.out.println("the authurl is =   " + authUrl);
		    response.sendRedirect(authUrl);
		} catch (DuoException e) {
			// TODO Auto-generated catch block
			session.setAttribute("jaas","failure");
			request.getRequestDispatcher("/loginhandler").forward(request,response);
			e.printStackTrace();
		}
		 
		 	

		   
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
