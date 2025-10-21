<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee ログアウト確認画面</title>
</head>
<body>
<!--ヘッダー-->
<main class="wrapper">
  <h2>Logout</h2>
		<p class="msg_info">
		${user.name}さんがログインチュウ🐭<br>
		ログアウトしますか？</p>
		<form action="Logout" method="post">
		<input type="submit" name="logout" value="ログアウト" class="nav_btn">
		<button type="button" onclick="history.back()" class="nav_btn">
		<i class="fa-solid fa-arrow-rotate-left"></i>戻る</button>
		</form>
	</main>
<!--	フッター-->
</body>
</html>