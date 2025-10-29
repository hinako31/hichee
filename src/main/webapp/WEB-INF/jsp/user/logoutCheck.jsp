<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee ログアウト確認画面</title>

<!-- 共通CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<!-- Googleフォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">

<!-- Font Awesome（アイコン用）-->
<script src="https://kit.fontawesome.com/your-kit-id.js" crossorigin="anonymous"></script>
</head>


<body>
<div class="outerWrapper">
  <div class="login-card">

    <!-- ヘッダー -->
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>

    <!-- メインコンテンツ -->
    <div class="form-area">
      <h2 class="login-title">Logout</h2>

      <p class="confirm-msg">
        ${user.name} さんがログインチュウ🐭<br>
        ログアウトしますか？
      </p>
      
       <div class="button-area">
		<form action="Logout" method="post">
        <button type="submit" name="logout" value="ログアウト">ログアウト</button>
		<button type="button" onclick="history.back()" class="nav_btn">
		<i class="fa-solid fa-arrow-rotate-left"></i>戻る
		 </button>
        </form>
      </div>
    </div>

    <!-- フッター -->
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>

  </div>
</div>
</body>
</html>