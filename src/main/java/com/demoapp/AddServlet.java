package com.demoapp;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import de.taimos.totp.TOTP;

//@WebServlet(name="add",urlPatterns={"/add"}) 
public class AddServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		
		System.out.println("add route" + req.getParameter("name") + req.getParameter("email"));
		String page="";
		HttpSession session=req.getSession();  
        session.setAttribute("uname",req.getParameter("name"));
        session.setAttribute("uemail",req.getParameter("email"));
        session.setAttribute("jaas","attempting");
        
        if((String)session.getAttribute("isLoggedIn")=="true")
        {
        	
        	res.sendRedirect("./welcome");
        	return;
        }
        
        
        if(req.getParameter("email") == null || req.getParameter("name")==null)
        {
        	res.sendRedirect("./auth.jsp");
        	return;
        }
      
        	
		String secretKey = generateSecretKey();
		
		
		BitMatrix bm = new BitMatrix(100);
		
		boolean hasAccount = false;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			session.setAttribute("jaas","failure");
			
			e1.printStackTrace();
		}
		
		try {
		
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","Prasanna@27");
		
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from testtable");
		
			rs = st.executeQuery("select * from testtable where address = '" +req.getParameter("email").toLowerCase()+  "'" );//+ " ' " +  req.getParameter("email").toLowerCase() + "'" 
			
			
			if(rs.next())
			{
				hasAccount = true;
				session.setAttribute("secretKey", rs.getString("secretkey"));
				session.setAttribute("generatedSecretKey",secretKey);
			}
			else
			{
				session.setAttribute("newUser","true");
				session.setAttribute("secretKey",secretKey);
			}
			
			st.close();
			con.close();
			}
		catch(SQLException se)
			{
				se.printStackTrace();
				session.setAttribute("jaas","failure");
				req.getRequestDispatcher("/loginhandler").forward(req,res);
				return;
			}
			
		
		
		
		String barCodeUrl = getGoogleAuthenticatorBarCode(secretKey, req.getParameter("email"), "company");
		
		
		
		try {
			bm = createQRCode(barCodeUrl,"./QRimage/",200,200);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("jaas","failure");
			
		}
		
		
	
		/*try (FileOutputStream out = new FileOutputStream("/Users/prasannanivas/eclipse-workspace/demoapp/src/main/webapp/qr11.png",false)) {
	        MatrixToImageWriter.writeToStream(bm, "png", out);
	    }*/
		 
		
		System.out.println("hasaccount = " + hasAccount);
		if(!hasAccount)
			req.setAttribute("source",bm);
		
        req.getRequestDispatcher("./add.jsp").forward(req, res);
			
		
		

	}
        

	
	public static String generateSecretKey() {
	    SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[20];
	    random.nextBytes(bytes);
	    Base32 base32 = new Base32();
	    return base32.encodeToString(bytes);
	}
	
	
	
	public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
	    try {
	        return "otpauth://totp/"
	                + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
	                + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
	                + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
	    } catch (UnsupportedEncodingException e) {
	        throw new IllegalStateException(e);
	    }
	}
	public static BitMatrix createQRCode(String barCodeData, String filePath, int height, int width)
	        throws WriterException, IOException {
	    BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
	            width, height);
	    return matrix;
	}

}
