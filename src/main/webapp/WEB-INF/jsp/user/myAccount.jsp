<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee アカウント管理画面</title>
</head>
<body>
<!--ヘッダー-->
<main class="wrapper">
     <h2>My Account</h2>
<form action="ChangeProfile" method="get">
<input type="submit" name="changeprofile" value="Change Profile" class="nav_btn">
</form>
</form>
	<form action="Logout" method="get">
	<input type="submit" name="logout" value="Logout" class="nav_btn">
	</form>
	<form action="Resign" method="get">
	<input type="submit" name="resign" value="Resign(退会)" class="nav_btn">
	</form><br>
	<form action="Login" method="post">
	<input type="submit" name="mypage" value="戻る" class="nav_btn">
	</form>
</main>	
<!--フッター-->
</body>
</html>