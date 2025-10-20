<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 会員登録フォーム</title>
</head>
<body>
<main class="wrapper">
<h2>New Account</h2>

 <c:if test="${not empty error}">
		        <p style="color:red;">${error}</p>
		    </c:if>
		    
 <form action="Regist" method="post" class="regist">
<p>
<label for="name">ニックネーム：
<input type="text" name="name" value="${name}"></label>
</p>
<p> <label for="email">メールアドレス：
<input type="email" name="email" value="${email}"></label>
</p>
<p>
<label for="pass">パスワード(4文字以上)：
<input type="password" name="pass" value=""></label>
</p>
<p>
<label for="confirmPass">確認用パスワード：
 <input type="password" name="confirmPass" value=""></label>
</p>
<input type="submit" name="check" value="確認" class="nav_btn"><br>
<input type="submit" name="back" value="戻る" class="nav_btn"><br>
</form>
</main>
<!--フッター-->
</body>
</html>