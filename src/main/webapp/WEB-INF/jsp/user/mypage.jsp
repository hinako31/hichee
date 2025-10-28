<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hichee マイページ画面</title>
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
      <h2 class="login-title">My Page</h2>

      <div class="button-area-vertical">
        <form action="NewCheese" method="get">
          <button type="submit" name="newcheese" value="New Cheese">New Cheese</button>
        </form>

        <form action="MyCheese" method="post">
          <button type="submit" name="action" value="My Cheese">My Cheese</button>
        </form>

        <form action="Account" method="get">
          <button type="submit" name="account" value="Me">Me</button>
        </form>
      </div>

      <p class="confirm-msg">
        ${user.name}さんの<br>
        Cheese Diaryは${diaryList.size()}件でチュ～🐭
      </p>
    </div>

    <footer>
      <jsp:include page="/WEB-INF/jsp/inc/footer.jsp" />
    </footer>
  </div>
</div>
</body>
</html>
