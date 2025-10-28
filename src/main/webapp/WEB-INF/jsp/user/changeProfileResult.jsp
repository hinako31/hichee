<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee ユーザー情報変更完了画面</title>
</head>
<body>
<!--ヘッダー-->
<main class="wrapper">
 <h2>Change Profile</h2>
		<p class="msg_info">変更が完了しました🧀</p>
		<a href="${pageContext.request.contextPath}/Account" class="nav_btn">My Accountへ</a>
	</main>
<jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
</body>
</html>