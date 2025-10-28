<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee 会員登録フォーム</title>
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
      <h2 class="login-title">New Account</h2>

      <div class="error">
        <c:if test="${not empty error}">
          <p style="color:red;">${error}</p>
        </c:if>
      </div>

      <form action="Regist" method="post">
        <div class="form-row">
          <label for="name">ニックネーム：</label>
          <input type="text" name="name" id="name" value="${name}">
        </div>

        <div class="form-row">
          <label for="email">メールアドレス：</label>
          <input type="email" name="email" id="email" value="${email}">
        </div>

        <div class="form-row">
          <label for="pass">パスワード(4文字以上):</label>
          <input type="password" name="pass" id="pass">
        </div>

        <div class="form-row">
          <label for="confirmPass">パスワード(確認用)：</label>
          <input type="password" name="confirmPass" id="confirmPass">
        </div>

        <div class="button-area">
          <button type="submit" name="check" value="確認">確認</button>
          <button type="submit" name="back" value="戻る">戻る</button>
        </div>
      </form>
    </div>

    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>
  </div>
</div>
</body>
</html>