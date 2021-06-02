<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <title>Insert title here</title>
   <script src="https://apis.google.com/js/platform.js" async defer></script>
   <meta name="google-signin-client_id" content="718315165204-gfm4i0hal9trjqo1gq8oe6bodm7ap6bp.apps.googleusercontent.com">
</head>
<body>
	
   	<div class="g-signin2" data-onsuccess="onSignIn" id="myP" ></div>
   	
	
	
      <img id="myImg"><br>
      <p id="name"></p>
      <div id="status">
      
      
   	</div>
   	
     <div id = "verify"></div>
   
   <script type="text/javascript">
      function onSignIn(googleUser) {
    	  console.log("called me");
      var profile = googleUser.getBasicProfile();
      var imagurl=profile.getImageUrl();
      var name=profile.getName();
      var email=profile.getEmail();
      console.log("name = " + name);
      console.log("email = " + email);
      localStorage.setItem("name",name);
      localStorage.setItem("email" , email);
      localStorage.setItem("imgurl" , imagurl);
      
      
      document.getElementById("verify").innerHTML = "<form style=\"display:none;\" position=\"absolute\" method=\"post\" action=\"j_security_check\"><input type=\"text\" name = \"name\" value = "+name+"><input type=\"text\" name = \"email\" value = "+email+"><input id=\"redirbtn\" type=\"submit\"></form>"
      document.getElementById('redirbtn').click();
      //document.getElementById("verify").innerHTML = "<a href = \"./add?email=" +email+ "&name=" +name+"\">Click here to verify</a>";
      //window.location.href = "./add?email=" +email+ "&name=" +name;
      
      
   }
   </script>
   
   <button onclick="myFunction()">Sign Out</button>
   <script>
      function myFunction() {
      gapi.auth2.getAuthInstance().disconnect();
      location.reload();
   }
   </script>
   
   
</body>
</html>