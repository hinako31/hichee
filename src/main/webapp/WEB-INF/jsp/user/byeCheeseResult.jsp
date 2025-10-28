<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Diary" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 削除完了画面</title>
</head>
<body>
<!--ヘッダー-->
<main class="wrapper">
<h2>Bye Cheese</h2>
	<p class="msg_info">Cheese Diaryを削除しました🧀</p>
	<form action="MyCheese" method="get">
	<input type="submit" name="mycheese" value="My Cheese一覧へ" class="nav_btn">
		</form>
	</main>
	<jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
</body>
</html>