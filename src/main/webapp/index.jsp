<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hichee ログイン/会員登録</title>
</head>
<body>

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
	    <button type="submit" name="action" value="login">ログイン</button>
	    <button type="submit" name="action" value="regist">会員登録</button>
	</form>
	</div>
</main>
</body>
</html>