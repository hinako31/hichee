<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- リンク -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <!-- フォント -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Prompt:wght@900&display=swap" rel="stylesheet">

    <!-- fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
        integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Hichee ログイン/会員登録</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<div class="outerWrapper">
<body>
  <div class="login-card">
  <header><jsp:include page="/WEB-INF/jsp/inc/header.jsp" />
  </header>
<div class="form-area">
    <h2 class="login-title">Login</h2>
    

    <div class="error">
      <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
      </c:if>
    </div>

    <form action="Login" method="post">
      
        <div class="form-row">
          <label for="email">メールアドレス：</label>
          <input type="text" name="email" id="email">
        </div>
        <div class="form-row">
          <label for="pass">パスワード：</label>
          <input type="password" name="pass" id="pass">
        </div>
        <div class="button-area">
          <button type="submit" name="action" value="login">ログイン</button>
          <button type="submit" name="action" value="regist">会員登録</button>
        </div>
      
    </form>
      </div>

    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>
  </div>
</body>
</html>