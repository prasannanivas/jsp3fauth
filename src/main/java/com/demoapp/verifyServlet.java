package com.demoapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import de.taimos.totp.TOTP;

/**
 * Servlet implementation class verifyServlet
 */
@WebServlet("/verifyServlet")
public class verifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public verifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username;
		HttpSession session=request.getSession(false);  
        username = (String)session.getAttribute("uemail");  
		if( request.getParameter("otp"). equals (getTOTPCode((String)session.getAttribute("secretKey"))))
		{
			
			session.setAttribute("otpverified", "true");
			response.sendRedirect("./duo?name="+username);
		}
		else
		{
			//response.sendRedirect("./auth.jsp");
			response.sendRedirect("./auth.jsp");
		}
		
	}
	
	
	public static String getTOTPCode(String secretKey) {
	    Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(secretKey);
	    String hexKey = Hex.encodeHexString(bytes);
	    return TOTP.getOTP(hexKey);
	}

}
