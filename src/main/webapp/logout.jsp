<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="UTF-8">
<script src="https://apis.google.com/js/platform.js" async defer></script>
   <meta name="google-signin-client_id" content="718315165204-gfm4i0hal9trjqo1gq8oe6bodm7ap6bp.apps.googleusercontent.com">
   <script type="text/javascript" src="./auth.jsp"></script>
</head>
<body>

<div id="gogle" style="display:none">
<div class="g-signin2" data-onsuccess="onSignIn" id="logogoogle"></div>
</div>

<h1>Logging you out....</h1>
<img src = "https://dribbble.com/shots/10805525-Still-Wait"/>

<button id = "logoutbtn" style="display:none" onclick="myFunction()">Sign Out</button>
   <script>
      function myFunction() {
    	  
    	  if(!gapi.auth2)
    		{
    		  location.reload();
    		  window.localStorage.clear();
              window.sessionStorage.clear();
    		  window.location.href = "./auth.jsp";
    		}
    	  else{
    	  gapi.auth2.getAuthInstance().disconnect();
          location.reload();
          window.localStorage.clear();
          window.sessionStorage.clear();
          var start = new Date().getTime();
          var end = start;
          window.location.href = "./auth.jsp";
    	  }
   }
   </script>
   
   <script>
   		document.getElementById("logoutbtn").click();
   </script>
</head>
<body>
	<script>
	
	</script>
</body>
</html>