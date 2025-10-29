
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee newCheese作成完了画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">
</head>
<body>
<div class="outerWrapper">
  <div class="login-card">
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>

    <div class="form-area">
      <h2 class="login-title">New Cheese</h2>

      <p class="confirm-msg">Cheese Diaryを作成しました🧀</p>

      <div class="button-area">
        <!-- My Page へ移動 -->
        <form action="Login" method="post">
         <button type="submit" name="mypage" value="My Pageへ">My Pageへ</button>
		</form>
      </div>
    </div>
    
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>
  </div>
</div>
</body>
</html>
