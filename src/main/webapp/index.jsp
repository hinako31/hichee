<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hichee ログイン/会員登録</title>
</head>
<body>
<div class="outerWrapper">
	<header class="wrapper">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/images/ZURU ZURU Do_logo.jpg" alt="">
        </div>
    </header>
    
<main class="wrapper">
   Login
	<div class="error">
		<c:if test="${not empty error}">
	    	<p style="color:red;">${error}</p>
		</c:if>
	</div>
<!--	ログインサーブレットで分岐処理-->
	<div class="loginform">
	<form action="Login" method="post">
	    <p class="formstyle"><label for="email">メールアドレス:<input type="text" name="email" id="email"></label></p>
	    <p class="formstyle"><label for="pass" class="pass">パスワード:<input type="password" name="pass" id="pass"></label></p>
	    <button type="submit" name="action" value="login" class="nav_btn vertical">ログイン</button>
	    <button type="submit" name="action" value="regist" class="nav_btn vertical">会員登録</button>
	</form>
	</div>
</main>
</body>
</html>