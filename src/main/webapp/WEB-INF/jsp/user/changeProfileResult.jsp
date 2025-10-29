<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee ユーザー情報変更完了画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">


<!-- Googleフォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">
</head>
<body>

<body>
<div class="outerWrapper">
  <div class="login-card">

    <!-- ヘッダー共通化 -->
    <header>
      <jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
    </header>

    <!-- メインコンテンツ -->
    <div class="form-area">
      <h2 class="login-title">Change Profile</h2>
      <p class="confirm-msg">変更が完了しました🧀</p>

      <div class="button-area">
	 <a href="${pageContext.request.contextPath}/Account">
          <button type="button">My Accountへ</button>
    </a>
      </div>
    </div>
      <!-- フッター共通化 -->
    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>

  </div>
</div>
</body>
</html>