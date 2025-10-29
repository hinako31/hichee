<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 退会完了画面</title>

<!-- 共通CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<!-- Googleフォント -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">

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
      <p class="confirm-msg">
        退会完了しました🧀<br>
        また会う日まで✨
      </p>

      <div class="button-area">
        <a href="${pageContext.request.contextPath}/Main">
          <button type="button">トップページへ</button>
        </a>
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
