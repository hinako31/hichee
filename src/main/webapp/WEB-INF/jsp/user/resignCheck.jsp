<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 退会確認画面</title>
</head>
<body>
<!--ヘッダー-->
<main class="wrapper">
<h2>Resign</h2>
		<p class="msg_info">本当に退会しますか？🐭</p>
		
				<p>ニックネーム：${sessionScope.user.name}<br>
				       メールアドレス：${sessionScope.user.email}<br>
				</p>
		
		<form action="Resign" method="post">
		<input type="submit" name="resign" value="退会" class="nav_btn">
		<button type="button" onclick="history.back()" class="nav_btn">
		<i class="fa-solid fa-arrow-rotate-left"></i>戻る</button>
		</form>
	</main>
<jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
</body>
</html>