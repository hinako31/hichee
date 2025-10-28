<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee アカウント管理画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">

</head>
<body>
<!--ヘッダー-->
<main class="outerWrapper">
 <div class="login-card">
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>
      <div class="form-area">
      <h2 class="login-title">My Account</h2>
      
     <div class="button-area-vertical">
      <form action="ChangeProfile" method="get">
       <button type="submit" name="changeprofile" value="Change Profile">Change Profile</button>
       
      </form>

	<form action="Logout" method="get">
	<button type="submit" name="logout" value="Logout">Logout</button>
	</form>
	
	<form action="Resign" method="get">
	<button type="submit" name="resign" value="Resign(退会)">Resign(退会)</button>
	</form><br>
	</div>
	</div>
	
	 <div class="button-area-vertical">
	<form action="Login" method="post">
	<button type="submit" name="mypage" value="戻る" class="back-button">戻る</button>
	</form>
	 </div>
	 <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>
</main>	

</div>
</div>
</body>
</html>