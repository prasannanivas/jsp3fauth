<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://apis.google.com/js/platform.js" async defer></script>
   <meta name="google-signin-client_id" content="718315165204-gfm4i0hal9trjqo1gq8oe6bodm7ap6bp.apps.googleusercontent.com">
   <script type="text/javascript" src="./auth.jsp"></script>
</head>
<body>

<div id="gogle" style="display:none">
<div class="g-signin2" data-onsuccess="onSignIn" id="logogoogle"></div>
</div>



<h1>Welcome!!!!!!</h1>
	<img id="myimg"/>
	<p id="name"></p>
	<h1>${name} </h1>
<script>

	if(!localStorage.getItem("imgurl"))
		window.location.href = "./auth.jsp";
	else
		{
	document.getElementById("myimg").src = localStorage.getItem("imgurl");
	document.getElementById("name").innerHTML = localStorage.getItem("name");
	localStorage.setItem("isLoggedIn",true);
		}
</script>



<button onclick="myFunction()">Sign Out</button>
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