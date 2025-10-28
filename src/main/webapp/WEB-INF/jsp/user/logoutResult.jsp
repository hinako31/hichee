<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--ヘッダー-->
	<main class="wrapper">
	<h2>Logout</h2>
		<p class="msg_info">ログアウトしました🧀</p>
		<form action="Logout" method="post">
		<input type="submit" name="top" value="トップページへ" class="nav_btn">
		</form>
	</main>
	<jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
</body>
</html>