<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee マイページ画面</title>

</head>
<body>
<!--ヘッダー-->
<main class="wrapper">
     <h2>My Page</h2>
<form action="NewCheese" method="get">
<input type="submit" name="newcheese" value="New Cheese" class="nav_btn">
</form>
</form>
	<form action="MyCheese" method="post">
	<input type="submit" name="action" value="My Cheese" class="nav_btn">
	</form>
	<form action="Account" method="get">
	<input type="submit" name="account" value="Me" class="nav_btn">
	</form>
	
	${user.name}さんの
	Cheese Diaryは${diaryList.size()}件でチュ～🐭
</body>
</html>