<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.google.zxing.qrcode.QRCodeWriter" %>
<%@ page import="com.google.zxing.common.BitMatrix" %>
<%@ page import="com.google.zxing.BarcodeFormat" %>
<%@ page import="com.google.zxing.client.j2se.MatrixToImageWriter" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.ByteArrayOutputStream"%>
<%@ page import="com.demoapp.BufferedImageUtil"%>

<%@ page import="javax.xml.bind.DatatypeConverter"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://apis.google.com/js/platform.js" async defer></script>
   <meta name="google-signin-client_id" content="718315165204-gfm4i0hal9trjqo1gq8oe6bodm7ap6bp.apps.googleusercontent.com">
   <script type="text/javascript" src="./auth.jsp"></script>
</head>

<div id="gogle" style="display:none">
<div class="g-signin2" data-onsuccess="onSignIn" id="logogoogle"></div>
</div>
</head>
<body>



<script>
if(!localStorage.getItem("imgurl"))
	window.location.href = "./auth.jsp";
else if(localStorage.getItem("isLoggedIn"))
	window.location.href = "./welcome";
	
</script>

<%BufferedImage bImage = null;
if(request.getAttribute("source")!=null)
{
	bImage = MatrixToImageWriter.toBufferedImage((BitMatrix)request.getAttribute("source"));
	System.out.println("inside jsp yes");
}
else
	System.out.println("inside jsp nooo");%>

	<img src = "<%=bImage!=null ? BufferedImageUtil.convert2DataURI(bImage, "png"):""%>" />
	
	<form action = "verify" method = "post">
		
		<input type = "text" name = "otp" placeholder="enter otp here"/>
		<input type="submit" value  = "verify"/>
	</form>
	
	<button onclick="myFunction()">go Back</button>
   <script>
      function myFunction() {
    	  gapi.auth2.getAuthInstance().disconnect();
          location.reload();
          window.localStorage.clear();
          window.sessionStorage.clear();
      window.location.href = "./logout";
   }
   </script>
	
</body>
</html>