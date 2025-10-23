<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hichee ログイン/会員登録</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

   <div class="login-card">
  <h1>Hi Cheese Diary</h1>
  <h2>Login</h2>

  <div class="error">
    <c:if test="${not empty error}">
      <p style="color:red;">${error}</p>
    </c:if>
  </div>

  <div class="form-area">
    <form action="Login" method="post">
      <p class="formstyle">
        <label for="email">メールアドレス：
          <input type="text" name="email" id="email">
        </label>
      </p>
      <p class="formstyle">
        <label for="pass">パスワード：
          <input type="password" name="pass" id="pass">
        </label>
      </p>
      <div style="text-align:center;">
        <button type="submit" name="action" value="login">ログイン</button>
        <button type="submit" name="action" value="regist">会員登録</button>
      </div>
    </form>
  </div>
</div>

</body>
</html>