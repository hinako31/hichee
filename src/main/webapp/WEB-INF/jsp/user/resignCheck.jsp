<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 退会確認画面</title>
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

    <!-- メインエリア -->
    <div class="form-area">
      <h2 class="login-title">Resign</h2>
      <p class="confirm-msg">本当に退会しますか？🐭</p>

      <div class="form-row">
				 <label>ニックネーム：</label>
        <strong>${sessionScope.user.name}</strong>
      </div>

      <div class="form-row">
        <label>メールアドレス：</label>
        <strong>${sessionScope.user.email}</strong>
      </div>
		
		  <div class="button-area">
		<form action="Resign" method="post">
		 <button type="submit" name="resign" value="退会">退会</button>
		<button type="button" onclick="history.back()" class="nav_btn">
		<i class="fa-solid fa-arrow-rotate-left"></i>戻る  </button>
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